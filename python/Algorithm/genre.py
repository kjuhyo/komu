from google.colab import drive
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import CountVectorizer
from ast import literal_eval
import pandas as pd
import numpy as np
import warnings
warnings.filterwarnings('ignore')

drive.mount('/content/gdrive')

song = pd.read_csv('/content/gdrive/My Drive/Colab Notebooks/kpop.csv')

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


def get_recommend_song_list(df, song_name, top=30):
    # 특정 노래와 비슷한 노래를 추천해야하기 때문에 특정노래 정보를 뽑아냄
    target_song_index = df[df['song_name'] == song_name].index.values
    # 코사인 유사도 중 비슷한 코사인 유사도를 가진 정보를 뽑아냄
    sim_index = genre_c_sim[target_song_index, :top].reshape(-1)

    # dataframe으로 만들어 반환
    result = df.iloc[sim_index][:10]
    return result


get_recommend_song_list(song_df, song_name='너랑 나')
