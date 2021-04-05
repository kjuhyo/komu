# from google.colab import drive
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import CountVectorizer
from ast import literal_eval
import pandas as pd
import numpy as np
import warnings
import sys
import json
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
    sql = f"select song_id from songlike where uid=\'{user_id}\';"
    curs.execute(sql)
    rows = curs.fetchone()
    song_list = []
    for i in range(len(rows)):
        song_list.append(rows[i])
    
    for i in range(len(song_list)):
        sql = f"select id from song where id={song_list[i]}"
        curs.execute(sql)
        rows = curs.fetchone()
        user_songs.append(rows[0])
    conn.close()


# warnings.filterwarnings('ignore')

# drive.mount('/content/gdrive')

# song = pd.read_csv('/content/gdrive/My Drive/Colab Notebooks/kpop.csv')

path = 'C:\\Users\\multicampus\\Desktop\\new2\\s04p23c104\\python\\Algorithm\\'
song = pd.read_csv(path + 'kpop.csv', low_memory=False)

song_df = song[['id', 'song_name', 'genre']]
# song_df.head(5)

# CountVectorizer를 적용하기 위해 공백문자로 word 단위가 구분되는 문자열로 변환.
song_df['genre_literal'] = song_df['genre'].apply(lambda x: (' ').join(x))

# ngram_range는 n-그램 범위.
# 장르 문자열을 숫자로 바꿔 벡터화 시킴
count_vect = CountVectorizer(ngram_range=(1, 3))
c_vector_genre = count_vect.fit_transform(song_df['genre'])
# c_vector_genre.shape

# 코사인 유사도를 구한 벡터를 미리 저장
genre_c_sim = cosine_similarity(
    c_vector_genre, c_vector_genre).argsort()[:, ::-1]
# genre_c_sim.shape


def get_recommend_song_list(df, song_id, top=30):
    # 특정 노래와 비슷한 노래를 추천해야하기 때문에 특정노래 정보를 뽑아냄
    target_song_index = df[df['id'] == song_id].index.values
    # 코사인 유사도 중 비슷한 코사인 유사도를 가진 정보를 뽑아냄
    sim_index = genre_c_sim[target_song_index, :top].reshape(-1)

    # dataframe으로 만들어 반환
    result = df.iloc[sim_index][:10]
    temp_list = list(np.array(result['id'].tolist()))
    song_id_list = []
    for i in range(len(temp_list)):
        song_id_list.append(int(temp_list[i]))
    return json.dumps(song_id_list)

# user_id = sys.argv[1]
user_id = 'prteUBReKZX2'
# song_id = int(song_id)
# song_id = 3625504
user_songs = []
read_data(user_id)
# print(user_songs)
song_id = user_songs[0]
print(get_recommend_song_list(song_df, song_id=song_id))