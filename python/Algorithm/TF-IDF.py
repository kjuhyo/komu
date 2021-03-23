import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity


# csv 파일 불러오기
path = 'C:\\Users\\multicampus\\Desktop\\멜론 차트 크롤링\\'
data = pd.read_csv(path + 'kpop.csv', low_memory=False)
data = data[data['lyric'] != '없음'].reset_index(drop=True)

# 영어의 불용어 처리
# 불용어란 and, i, me처럼 유의미하지 않은 단어 토큰을 제거해주라는 의미
tfidf = TfidfVectorizer(stop_words='english')

# lyric에 대해서 tf-idf 수행
tfidf_matrix = tfidf.fit_transform(data['lyric'])

# cosine_matrix 생성
cosine_matrix = cosine_similarity(tfidf_matrix, tfidf_matrix)

# song_name과 id를 매핑할 dictionary를 생성해줍니다. 
kpop2id = {}
for i, c in enumerate(data['song_name']): kpop2id[i] = c

# id와 song_name을 매핑할 dictionary를 생성해줍니다. 
id2kpop = {}
for i, c in kpop2id.items(): id2kpop[c] = i

# 찾을 노래의 id 추출 
idx = id2kpop['Dynamite']
# 자기 자신을 제외한 영화들의 유사도 및 인덱스를 추출
sim_scores = [(i, c) for i, c in enumerate(cosine_matrix[idx]) if i != idx]
# 유사도가 높은 순서대로 정렬
sim_scores = sorted(sim_scores, key = lambda x: x[1], reverse=True)

# 상위 10개의 인덱스와 유사도를 추출
sim_scores[0:10]
sim_scores = [(kpop2id[i], score) for i, score in sim_scores[0:10]]
print(sim_scores)