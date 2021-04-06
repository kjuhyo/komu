from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from konlpy.tag import Okt
import pandas as pd
import numpy as np
import csv
import re
import json
import sys
import pymysql


def read_data(user_id):
    # connection 정보
    conn = pymysql.connect(
        host = 'j4c104.p.ssafy.io',
        user = 'root',
        password = 'root',
        db = 'komu',
        charset = 'utf8'
    )
    curs = conn.cursor()
    sql = f"select namu_id from wordlike where uid=\'{user_id}\';"
    curs.execute(sql)
    rows = curs.fetchone()
    namu_id_list = []
    for i in range(len(rows)):
        namu_id_list.append(rows[i])
    
    for i in range(len(namu_id_list)):
        sql = f"select namu_title from namuwiki where namu_id={namu_id_list[i]}"
        curs.execute(sql)
        rows = curs.fetchone()
        user_words.append(rows[0])
    conn.close()


# 한글, 숫자, 영어 빼고 전부 제거
def sub_special(s):
    return re.sub(r'[^ㄱ-ㅎㅏ-ㅣ가-힣0-9a-zA-Z ]','',s)


STOP_WORDS = ['의','가','다','이','은','들','늘','는','좀','잘','걍','과','도','를','을','으로','자','에','와','한','하다', '로']
okt = Okt()


# 언어 전처리
def morph_and_stopword(s):
    token_ls = []
    #형태소 분석
    tmp = okt.morphs(s, stem=True)

    #불용어 처리
    for token in tmp:
        if token not in STOP_WORDS:
            token_ls.append(token)
    
    return ' '.join(token_ls)


# 가사 전처리
# def pre_lyric(data):
#     for i in range(len(data)):
#         lyric_s = sub_special(data.iloc[i]['lyric'])
#         lyric = morph_and_stopword(lyric_s)
#         temp = []
#         song_id = data.iloc[i]['id']
#         song_name = data.iloc[i]['song_name']
#         temp.append(song_id)
#         temp.append(song_name)
#         temp.append(lyric)
#         with open('preLyric.csv', 'a', encoding='utf-8-sig', newline='') as f:
#             writer = csv.writer(f)
#             writer.writerow(temp)


# 전처리 되기 전 csv 파일 불러오기
# path = 'C:\\Users\\multicampus\\Desktop\\멜론 차트 크롤링\\'
# data = pd.read_csv(path + 'kpop.csv', low_memory=False)

# 처음 받을 때만 사용
# with open('preLyric.csv', 'w', encoding='utf-8-sig', newline='') as f:
#     writer = csv.writer(f)
#     writer.writerow(['id', 'song_name', 'pre_lyric'])

# pre_lyric(data)


def lyric_REC(song_name, cosine_matrix):
    #유저 인덱스 가져오기
    idx = indices[song_name]

    # 모든 가사에 대해서 해당 단어와의 유사도를 구하기
    sim_scores = list(enumerate(cosine_matrix[idx]))

    # 유사도에 따라 가사들을 정렬
    sim_scores = sorted(sim_scores, key=lambda x:x[1], reverse = True)

    # 가장 유사한 10개의 가사를 받아옴
    sim_scores = sim_scores[1:11]

    # 가장 유사한 10개 가사의 인덱스 받아옴
    lyric_indices = [i[0] for i in sim_scores]
    
    #기존에 읽어들인 데이터에서 해당 인덱스의 값들을 가져온다. 그리고 스코어 열을 추가하여 코사인 유사도도 확인할 수 있게 한다.
    result_df = data.iloc[lyric_indices].copy()
    result_df['score'] = [i[1] for i in sim_scores]
    
    # 읽어들인 데이터에서 가사 부분만 제거, 노래 제목과 스코어만 보이게 함
    del result_df['pre_lyric']
    # print(result_df)

    lyric_rec_list = []
    for i in range(len(result_df)):
        lyric_rec_list.append(int(result_df.iloc[i]['id']))
    # 가장 유사한 10개의 노래의 id와 제목이 있는 리스트 리턴
    return json.dumps(lyric_rec_list)


# 전처리 된 csv 파일 불러오기
path = 'C:\\Users\\multicampus\\Desktop\\ssafy\\PJT 2\\Sub PJT 3\\s04p23c104\\python\\Algorithm\\'
data = pd.read_csv(path + 'preLyric.csv', low_memory=False)

# data의 전처리된 가사가 null값이거나 '없다'인 경우를 제거해준다.
data = data[data['pre_lyric'].notnull()].reset_index(drop=True)
data = data[data['pre_lyric'] != '없다'].reset_index(drop=True)

# user의 좋아하는 단어 data에 등록
user_id = sys.argv[1]
user_words = []
read_data(user_id)

user_lyric = ' '.join(user_words)
data=data.append({'id' : 999999, 'song_name' : 'user_words', 'pre_lyric' : user_lyric} , ignore_index=True)

# 영어의 불용어 처리
# 불용어란 and, i, me처럼 유의미하지 않은 단어 토큰을 제거해주라는 의미
tfidf = TfidfVectorizer(stop_words='english')

# lyric에 대해서 tf-idf 수행
tfidf_matrix = tfidf.fit_transform(data['pre_lyric'])
# print(tfidf_matrix.shape)

# cosine_matrix 생성
cosine_matrix = cosine_similarity(tfidf_matrix, tfidf_matrix)
# print(cosine_matrix)

# 인덱스 테이블 생성
indices = pd.Series(data.index, index=data['song_name']).drop_duplicates()

# user_id = input()
# print(user_id)
print(lyric_REC('user_words', cosine_matrix))
