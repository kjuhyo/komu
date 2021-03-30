<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-50 mx-auto"></div>
          </div>
          <div class="komuwiki_container">
            <div class="komuwiki_header">
              <div class="komuwiki_title">
                전지현
              </div>
              <div class="komuwiki_option_area">
                <b-icon
                  class="wiki_option_icon"
                  icon="heart"
                  font-scale="2"
                ></b-icon>
                <b-icon
                  class="wiki_option_icon"
                  icon="pencil"
                  font-scale="2"
                ></b-icon>
              </div>
            </div>

            <hr />
            <!-- 내용 -->
            <div class="komuwiki_body">
              <!-- 이미지 -->
              <div class="m_komuwiki_body_img" v-if="isMobile">
                <img class="k_body_img" src="@/assets/img/jihyeon.jpg" />
              </div>

              <div class="komuwiki_body_img" v-else>
                <img class="k_body_img" src="@/assets/img/jihyeon.jpg" />
              </div>

              <!-- 내용 -->

              <div class="m_komuwiki_body_content" v-if="isMobile">
                1997년 17살에 전지현은 아는 모델 언니를 따라갔다가 패션잡지
                '에꼴'의 표지모델로 발탁되어 연예계에 데뷔하였다. 이를 눈여겨본
                당시의 대형기획사 싸이더스의 정훈탁 대표에 의해 1년간 연기
                수업을 받으면서 여러 편의 광고에 등장하였다. 광고 속 전지현을
                눈여겨 본 오종록 PD는 1998년 드라마 《내 마음을 뺏어봐》에
                캐스팅 제의를 했고, 이를 통해 드라마 데뷔를 해 전지현이라는
                예명으로 활동을 시작하였다. 이때 SBS 인기가요의 MC로도
                발탁되었는데, 첫 방송부터 멘트 실수가 터지면서 당장 MC를
                바꾸라는 쓴소리와 함께 스포츠서울에서는 MC 자격 미달이라는
                기사까지 나왔었다. 하지만 시간이 지날수록 진행이 안정되어 처음
                같은 불호 반응은 점차 사그라들었다. 연예계에 본격적으로 뛰어든
                1년 뒤 1999년은 라이징 스타로서의 자리를 확고히 한 해이다. 우선
                《화이트 발렌타인》이라는 영화에 출연하며 스크린 데뷔를 하였다.
                드라마 《해피투게더》에도 출연했는데 시청률 37.9%까지 나오며
                상당히 성공한 작품이다. 무엇보다 전지현을 비롯해 송승헌, 이병헌,
                김하늘, 차태현 등 현재는 내로라하는 탑스타들이 출연했던
                드라마이기 때문에 아직까지도 화자되는 드라마이기도 하다. 같은 해
                삼성 마이젯 프린트 광고는 전지현이라는 이름을 대중에게 알리게
                되는 계기이다. 음악에 맞춰 테크노 댄스를 추는 전지현의 모습에
                많은 사람들의 주목을 이끌었다. 1999년 백상예술대상 신인여우상,
                SBS 연기대상 신인상을 받으며 탑스타가 될 발판을 마련하였다.
                2000년에는 이정재와 함께 영화 《시월애》에 출연하였다. 같은
                시기에 개봉한 비슷한 소재 영화의 흥행으로 흥행에는 실패하였지만
                영상미로는 꽤 인정을 받은 영화이다. 이후에 한국영화 최초로
                '레이크 하우스'라는 제목으로 할리우드에서 산드라 블록, 키아누
                리브스의 주연으로 리메이크되기도 했다.
              </div>
              <div class="komuwiki_body_content" v-else>
                1997년 17살에 전지현은 아는 모델 언니를 따라갔다가 패션잡지
                '에꼴'의 표지모델로 발탁되어 연예계에 데뷔하였다. 이를 눈여겨본
                당시의 대형기획사 싸이더스의 정훈탁 대표에 의해 1년간 연기
                수업을 받으면서 여러 편의 광고에 등장하였다. 광고 속 전지현을
                눈여겨 본 오종록 PD는 1998년 드라마 《내 마음을 뺏어봐》에
                캐스팅 제의를 했고, 이를 통해 드라마 데뷔를 해 전지현이라는
                예명으로 활동을 시작하였다. 이때 SBS 인기가요의 MC로도
                발탁되었는데, 첫 방송부터 멘트 실수가 터지면서 당장 MC를
                바꾸라는 쓴소리와 함께 스포츠서울에서는 MC 자격 미달이라는
                기사까지 나왔었다. 하지만 시간이 지날수록 진행이 안정되어 처음
                같은 불호 반응은 점차 사그라들었다. 연예계에 본격적으로 뛰어든
                1년 뒤 1999년은 라이징 스타로서의 자리를 확고히 한 해이다. 우선
                《화이트 발렌타인》이라는 영화에 출연하며 스크린 데뷔를 하였다.
                드라마 《해피투게더》에도 출연했는데 시청률 37.9%까지 나오며
                상당히 성공한 작품이다. 무엇보다 전지현을 비롯해 송승헌, 이병헌,
                김하늘, 차태현 등 현재는 내로라하는 탑스타들이 출연했던
                드라마이기 때문에 아직까지도 화자되는 드라마이기도 하다. 같은 해
                삼성 마이젯 프린트 광고는 전지현이라는 이름을 대중에게 알리게
                되는 계기이다. 음악에 맞춰 테크노 댄스를 추는 전지현의 모습에
                많은 사람들의 주목을 이끌었다. 1999년 백상예술대상 신인여우상,
                SBS 연기대상 신인상을 받으며 탑스타가 될 발판을 마련하였다.
                2000년에는 이정재와 함께 영화 《시월애》에 출연하였다. 같은
                시기에 개봉한 비슷한 소재 영화의 흥행으로 흥행에는 실패하였지만
                영상미로는 꽤 인정을 받은 영화이다. 이후에 한국영화 최초로
                '레이크 하우스'라는 제목으로 할리우드에서 산드라 블록, 키아누
                리브스의 주연으로 리메이크되기도 했다.
              </div>
            </div>
            <hr />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/komuwikidetail.scss';
export default {
  components: {},
  data() {
    return {
      isMobile: false,
    };
  },
  bodyClass: 'profile-page',
  props: {
    header: {
      type: String,
      default: require('@/assets/img/city-profile.jpg'),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  mounted() {
    this.onResize();
    window.addEventListener('resize', this.onResize);
  },
  methods: {
    onResize: function() {
      this.isMobile = window.innerWidth <= 480;
    },
  },
};
</script>
