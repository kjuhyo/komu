from selenium import webdriver
from bs4 import BeautifulSoup
import requests
import time
import csv
import re
import pandas as pd


def openDriver():
    url = 'https://www.melon.com/chart/index.htm'
    driver = webdriver.Chrome()
    driver.implicitly_wait(3)
    driver.get(url)
    time.sleep(1)
    return driver


def Crawling(song_id, song_name):
    headers = {
        'User-Agent': ('Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 '
                    '(KHTML, like Gecko) Chrome/68.0.3440.75 Safari/537.36')
    }
    front_url = 'https://www.melon.com/song/detail.htm?songId='
    song_url = front_url + song_id
    response = requests.get(song_url, headers=headers)
    soup = BeautifulSoup(response.text, 'html.parser')
    singer_html = soup.select('.wrap_info .artist a')
    
    ### 가수
    singer_s = []
    if len(singer_html) != 0:
        for html in singer_html:
            singer_s.append(html['title'])
    else:
        # url 없는 Various Artists용
        singer_html = str(soup.select('.wrap_info .artist')[0])
        singer_html = singer_html.replace('\t','').replace('\r','').split('\n')
        singer_html = ''.join(singer_html)
        matched = re.search(r">(.*)<", singer_html)
        singer_s.append(matched.group(1))

    # 가수가 여러명일 때 하나의 string으로 표현
    singer_s = ', '.join(singer_s)


    # 앨범명
    album_name_html = str(soup.select('.list dd')[0])
    matched = re.search(r">(.*)<", album_name_html)
    matched2 = re.search(r">(.*)<", matched.group(1))
    album_name = matched2.group(1).strip()

    # 앨범커버
    album_cover = soup.select_one('.image_typeAll > img')['src']

    # 발매날짜
    song_date_html = str(soup.select('.list dd')[1])
    matched = re.search(r">(.*)<", song_date_html)
    song_date = matched.group(1)


    # 장르
    song_genre_html = str(soup.select('.list dd')[2])
    matched = re.search(r">(.*)<", song_genre_html)
    song_genre = matched.group(1)
    song_genre = song_genre.replace('amp;', '')
    # 외국 음악 장르 제거
    gen_temp = ''
    for gen in song_genre.split(', '):
        if gen == 'POP':
            return
        if gen == 'J-POP':
            return
        if gen == '국외영화':
            return

    ### 가사가 있으면 추출
    try:
        lyric_html = str(soup.select('.section_lyric .wrap_lyric .lyric')[0])
        lyric_html = lyric_html.replace('\t','').replace('\r','').split('\n')
        lyric_html = ''.join(lyric_html)

        matched = re.search(r"-->(.*)<br/>", lyric_html)
        lyric = matched.group(1).strip()
        lyric = lyric.replace('<br/>', '\n')

        #가사 앞뒤 빈칸 제거
        lyric_list = []
        for line in lyric.split('\n'):
            lyric_list.append(line.strip())
        lyric = ('\n').join(lyric_list)

    except:
        lyric = "없음"


    if singer_s == 'Various Artists':
        singer_img = ''
    else:
        singer_img = soup.select_one('.thumb_atist > img')['src']

    temp = []
    temp.append(song_id)
    temp.append(song_name)
    temp.append(singer_s)
    temp.append(singer_img)
    temp.append(album_name)
    temp.append(album_cover)
    temp.append(song_genre)
    temp.append(song_date)
    temp.append(lyric)
    song_id_list.append(song_id)
    with open('kpop.csv', 'a', encoding='utf-8-sig', newline='') as f:
        writer = csv.writer(f)
        writer.writerow(temp)


# 처음 받을 때만 사용
# with open('kpop06.csv', 'w', encoding='utf-8-sig', newline='') as f:
#     writer = csv.writer(f)
#     writer.writerow(['id', 'song_name', 'singer_name', 'singer_img', 'album_name', 'album_cover', 'genre', 'issue_date', 'lyric'])


# 다시 받을 때 song_id 확인
csv_test = pd.read_csv('kpop.csv', encoding='utf-8')
song_id = csv_test['id']
song_id_val = song_id.values
song_id_list = song_id_val.tolist()
for i in range(len(song_id_list)):
    song_id_list[i] = str(song_id_list[i])


driver = openDriver()
driver.find_element_by_class_name('btn_chart_f').click()
driver.implicitly_wait(3)
time.sleep(1)
driver.find_element_by_xpath('//*[@id="d_chart_search"]/div/h4[2]/a').click()

# song_id_list = []
# 연대
driver.find_element_by_xpath(f'//*[@id="d_chart_search"]/div/div/div[1]/div[1]/ul/li[3]').click()
# 연도
driver.find_element_by_xpath(f'//*[@id="d_chart_search"]/div/div/div[2]/div[1]/ul/li[10]').click()
# 월간
driver.find_element_by_xpath(f'//*[@id="d_chart_search"]/div/div/div[3]/div[1]/ul/li[12]').click()
# 장르/스타일
driver.find_element_by_xpath(f'//*[@id="d_chart_search"]/div/div/div[5]/div[1]/ul/li[1]').click()
# 검색
driver.find_element_by_xpath('//*[@id="d_srch_form"]/div[2]/button/span/span').click()
flag = True
for j in range(1, 101):
    if (j > 50 and flag):
        driver.find_element_by_xpath('//*[@id="frm"]/div[2]/span/a').click()
        flag = False
    try:
        if driver.find_element_by_xpath(f'/html/body/div/div[3]/div/div/div/div[1]/div[2]/form/div[1]/table/tbody/tr[{j}]/td[4]/div/a').get_attribute('href'):
            s = driver.find_element_by_xpath(f'/html/body/div/div[3]/div/div/div/div[1]/div[2]/form/div[1]/table/tbody/tr[{j}]/td[4]/div/a').get_attribute('href')
            s = s.split('(\'')
            s = s[1]
            s = s.split('\'')
            song_id = s[0]
            if song_id in song_id_list:
                continue
            else:
                song_name = driver.find_element_by_xpath(f'/html/body/div/div[3]/div/div/div/div[1]/div[2]/form/div[1]/table/tbody/tr[{j}]/td[4]/div/div/div[1]/span').text
                print(song_name)
                try:
                    Crawling(song_id, song_name)
                except:
                    print(f'{j}번째 곡정보가 없습니다.')
    except:
        print(j)
        print('-----------------------에러 발생--------------------------------')
        time.sleep(2)
        break
