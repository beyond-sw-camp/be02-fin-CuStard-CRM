<!-- <template>
  <div class="div">
    <div class="div2">
      <p>패션</p>
      <p>앞서나가는 패션 아이템으로 만드는 새로운 스타일!</p>
    </div>
  </div>
    <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
      <Slide v-for="(slide, index) in slides" :key="index">
        <div class="carousel__slide product">
          <div class="carousel__item-column">
            <img :src="slide.image" alt="Slide Image">
          </div>
          <div class="carousel__item-column">
            <div class="carousel__item-details">
              <div class="carousel__item-title">{{ slide.title }}</div>
              <div class="carousel__item-price">{{ slide.price }}</div>
            </div>
          </div>
        </div>
      </Slide>
  
      <template #addons>
        <Navigation />
        <Pagination />
      </template>
    </Carousel>

    <div class="div">
    <div class="div2">
      <p>뷰티</p>
      <p>아름다움을 위한 최고의 선택!</p>
    </div>
  </div>
    <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
      <Slide v-for="(slide, index) in slides2" :key="index">
        <div class="carousel__slide product">
          <div class="carousel__item-column">
            <img :src="slide.image" alt="Slide Image">
          </div>
          <div class="carousel__item-column">
            <div class="carousel__item-details">
              <div class="carousel__item-title">{{ slide.title }}</div>
              <div class="carousel__item-price">{{ slide.price }}</div>
            </div>
          </div>
        </div>
      </Slide>
      <template #addons>
        <Navigation />
        <Pagination />
      </template>
    </Carousel>

    <div class="div">
    <div class="div2">
      <p>식품</p>
      <p>입맛을 사로잡을 제품!</p>
    </div>
  </div>

    <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
      <Slide v-for="(slide, index) in slides3" :key="index">
        <div class="carousel__slide product">
          <div class="carousel__item-column">
            <img :src="slide.image" alt="Slide Image">
          </div>
          <div class="carousel__item-column">
            <div class="carousel__item-details">
              <div class="carousel__item-title">{{ slide.title }}</div>
              <div class="carousel__item-price">{{ slide.price }}</div>
            </div>
          </div>
        </div>
      </Slide>
      <template #addons>
        <Navigation />
        <Pagination />
      </template>
    </Carousel>

    <div class="div">
    <div class="div2">
      <p>스포츠/레저/자동차</p>
      <p>액티브한 라이프스타일을 완벽하게 지원!</p>
    </div>
  </div>
  
    <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
      <Slide v-for="(slide, index) in slides4" :key="index">
        <div class="carousel__slide product">
          <div class="carousel__item-column">
            <img :src="slide.image" alt="Slide Image">
          </div>
          <div class="carousel__item-column">
            <div class="carousel__item-details">
              <div class="carousel__item-title">{{ slide.title }}</div>
              <div class="carousel__item-price">{{ slide.price }}</div>
            </div>
          </div>
        </div>
      </Slide>
      <template #addons>
        <Navigation />
        <Pagination />
      </template>
    </Carousel>

    <div class="div">
    <div class="div2">
      <p>가전/디지털</p>
      <p>내 눈 앞에 펼쳐지는 더 스마트하고 편리한 삶!</p>
    </div>
  </div>
    <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
      <Slide v-for="(slide, index) in slides4" :key="index">
        <div class="carousel__slide product">
          <div class="carousel__item-column">
            <img :src="slide.image" alt="Slide Image">
          </div>
          <div class="carousel__item-column">
            <div class="carousel__item-details">
              <div class="carousel__item-title">{{ slide.title }}</div>
              <div class="carousel__item-price">{{ slide.price }}</div>
            </div>
          </div>
        </div>
      </Slide>
      <template #addons>
        <Navigation />
        <Pagination />
      </template>
    </Carousel>

  </template>
  
  <script>
  import { defineComponent } from 'vue'
  import { Carousel, Navigation, Pagination, Slide } from 'vue3-carousel'
  
  import 'vue3-carousel/dist/carousel.css'
  
  export default defineComponent({
    name: 'App',
    components: {
      Carousel,
      Slide,
      Pagination,
      Navigation,
    },
    data() {
      return {
        slides: [
          { image: 'path/to/image1.jpg', title: '트위드 자켓', price: '159000원' },
          { image: 'path/to/image2.jpg', title: '이제훈 셔츠', price: '48000원' },
          { image: 'path/to/image3.jpg', title: '개발자 셔츠', price: '51000원' },
          { image: 'path/to/image4.jpg', title: 'Adidas Gazelle', price: '159000원' },
          { image: 'path/to/image5.jpg', title: 'NIKE 덩크', price: '198000원' },
          { image: 'path/to/image6.jpg', title: 'EASTPAK 백팩', price: '163000원' },
          { image: 'path/to/image6.jpg', title: 'Adidas SuperStar', price: '192000원' },
          { image: 'path/to/image6.jpg', title: '한소희 후드티', price: '199000원' }
        ],
        slides2: [
          { image: 'path/to/image1.jpg', title: '마쉬멜로우 월드 배쓰밤', price: '5000원' },
          { image: 'path/to/image2.jpg', title: '샤넬 넘버파이브 오 드 빠르팽 넘버 파이브', price: '136000원' },
          { image: 'path/to/image3.jpg', title: '청귤 구달세럼 ', price: '26000원' },
          { image: 'path/to/image4.jpg', title: '닥터자르트 시카페어', price: '35000원' },
          { image: 'path/to/image5.jpg', title: '톰포드 사하라 느와', price: '891000원' },
          { image: 'path/to/image6.jpg', title: '엘라스틴 샴푸', price: '12900원' },
          { image: 'path/to/image6.jpg', title: '엘라스틴 린스', price: '12900원' },
          { image: 'path/to/image6.jpg', title: '[마루는강쥐기획] 발을 씻자', price: '9900원' }
        ],
        slides3: [
          { image: 'path/to/image1.jpg', title: '로제 떡볶이', price: '18000원' },
          { image: 'path/to/image2.jpg', title: '레드불', price: '1200원' },
          { image: 'path/to/image3.jpg', title: '별빛 팡팡 아몬드 ', price: '3500원' },
          { image: 'path/to/image4.jpg', title: '우유속의 코코아', price: '1800원' },
          { image: 'path/to/image5.jpg', title: '이클립스 피치', price: '3200원' },
          { image: 'path/to/image6.jpg', title: '엽기 닭볶음탕', price: '$24000원' },
          { image: 'path/to/image6.jpg', title: '불고기 피자', price: '15000원' },
          { image: 'path/to/image6.jpg', title: '요거트 스무디', price: '4600원' }
        ],
        slides4: [
          { image: 'path/to/image1.jpg', title: '나이키 손목밴드', price: ' 28000원' },
          { image: 'path/to/image2.jpg', title: '여성용 스노우보드', price: '27000원' },
          { image: 'path/to/image3.jpg', title: '잔망루피 방향제', price: '33000원' },
          { image: 'path/to/image4.jpg', title: '푸시업 보드', price: '45000원' },
          { image: 'path/to/image5.jpg', title: '전기 자전거', price: '140000원' },
          { image: 'path/to/image6.jpg', title: 'SPALDING 농구공', price: '99000원' },
          { image: 'path/to/image6.jpg', title: '골프채', price: '820000원' },
          { image: 'path/to/image6.jpg', title: 'ELLE수영복', price: '200000원' }
        ],
        slides5: [
          { image: 'path/to/image1.jpg', title: '[BOSE] 헤드폰', price: '49000원' },
          { image: 'path/to/image2.jpg', title: 'APPLE 아이폰 15', price: '1090000원' },
          { image: 'path/to/image3.jpg', title: '소니 무선 헤드폰', price: '599000원' },
          { image: 'path/to/image4.jpg', title: '삼성 갤럭시 22', price: '1090000원' },
          { image: 'path/to/image5.jpg', title: 'APPLE iPad 15', price: '1320000원' },
          { image: 'path/to/image6.jpg', title: '삼성 갤럭시 탭', price: '1521000원' },
          { image: 'path/to/image6.jpg', title: '로지텍 마우스,', price: '95000원' },
          { image: 'path/to/image6.jpg', title: '레오폴드 무선 키보드', price: '399000원' }
        ],
      }
    }
  })
  </script>
  
  <style>
  .div{
    display: flex;
    flex-direction: column;
    align-items: center;
    align-content: stretch;

  }
  .div2{
    display: flex;
    flex-direction: column;
    justify-content: left;
  }
 .carousel__icon {
    width: var(--vc-icn-width);
    height: var(--vc-icn-width);
    fill: currentColor;
    border: 2px solid black;
    background-color: black;
    border-radius: 10px;
    color: white;
}
  .carousel__slide {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
  }
  
  
  .carousel__item-column {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    -webkit-line-clamp: 1;

  }
  
  .carousel__item {
    min-height: 200px;
    width: 100%;
    background-color: blue;
    color: var(--vc-clr-white);
    font-size: 20px;
    border-radius: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .carousel__slide {
    padding: 10px;
    width: 150px;
    height: 200px;
  }
  
  .carousel__item-details {
    text-align: center;
    margin-top: 10px;
    text-align: justify;
    /* border: 1px solid black; */
    width: 120px;
    height: 100px;
    overflow: hidden;  	
  text-overflow: ellipsis;  	
  white-space: nowrap; 	
  word-break:break-all;

  }
  
  .carousel__item-title {
    font-weight: bold;
  }
  
  .carousel__item-price {
    margin-top: 5px;
    font-size: 17px;
    font-weight: lighter;
  }

  .carousel__pagination-button{
    width: 17px;
  }

  </style>
   -->