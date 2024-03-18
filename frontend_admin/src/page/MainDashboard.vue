<template>
  <div class="container-scroller">
    <!-- partial:partials/_sidebar.html -->
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_navbar.html -->

      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <h6 class="text-muted font-weight-normal" style="padding-left:20px; padding-bottom: 5px;"> 오늘 03:00시 기준</h6>
          <div class="row">
            <div class="col-xl-2 col-sm-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">방문자 수</h4>
                      <div class="d-flex align-items-center align-self-start" style="width: 300px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500;">{{ visitorCount }}명</div>
                        <div :class="['ml-2', 'mb-0', 'font-weight-medium', visitorcalc >= 0 ? 'text-success' : 'text-danger']">
                          {{ visitorcalc }}명
                        </div>

                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', visitorcalc >= 0 ? 'text-success' : 'text-danger']">
                        <span :class="['mdi', 'icon-item', visitorcalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left']"></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-2 col-sm-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">결제건 수</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:150px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500;">{{ todayOrder }}건</div>
                        <div :class="['ml-2', 'mb-0', 'font-weight-medium', todayOrderCalc >= 0 ? 'text-success' : 'text-danger'] " > {{ todayOrderCalc }}건 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', todayOrderCalc >= 0 ? 'text-success' : 'text-danger']">
                        <span :class="['mdi', 'icon-item', todayOrderCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left']"></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-2 col-sm-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">신규 유입</h4>
                      <div class="d-flex align-items-center align-self-start" style="width:150px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500;">{{ newSignup }}명</div>
                        <div :class="[newSignupCalcClass,'ml-2', 'mb-0', 'font-weight-medium'] "> {{ newSignupCalc }}명 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', todayOrderCalc >= 0 ? 'text-success' : 'text-danger']">
                        <span :class="['mdi', 'icon-item', todayOrderCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left']"></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-2 col-sm-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">

                      <h4 class="text-muted font-weight-normal">휴면고객재접속</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:250px;">
                        <div style="font-size: 25px; font-weight: 500;">{{ dormatCs }}명</div>
                        <div :class="[dormatCsCalcClass ,'ml-2', 'mb-0', 'font-weight-medium'] "> {{ dormatCsCalc }}명 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', todayOrderCalc >= 0 ? 'text-success' : 'text-danger']">
                        <span :class="['mdi', 'icon-item', todayOrderCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left']"></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">하루 매출액</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:250px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500;">{{ todaySell }}원</div>
                        <div :class="[todaySellCalcClass, 'ml-2', 'mb-0', 'font-weight-medium'] "> {{ todaySellCalc }}원 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', todayOrderCalc >= 0 ? 'text-success' : 'text-danger']">
                        <span :class="['mdi', 'icon-item', todayOrderCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left']"></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-5  grid-margin stretch-card">
              <div class="card" >
                <div class="card-body">
                  <div class="chartjs-size-monitor">
                    <div class="chartjs-size-monitor-expand">
                      <div class=""></div>
                    </div>
                    <div class="chartjs-size-monitor-shrink">
                      <div class=""></div>
                    </div>
                  </div>
                  <h4 class="card-title">카테고리 별 판매율</h4>
                  <canvas
                      ref="CategoryChart"
                      id="doughnutChart"
                      style="height: 130px; display: block; width: 300px"
                      width="400"
                      height="130"
                      class="chartjs-render-monitor"
                  ></canvas>
                  <div
                      class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3" style="height:40px" >
                    <div> 지난 14일</div>
                    <div class="text-md-center text-xl-left">
                      <h6 class="mb-1">결제 수</h6>
                    </div>
                    <div
                        class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                      <h6 class="font-weight-bold mb-0">{{this.ordersCount}} 건</h6>
                    </div>
                  </div>
                  <div
                      class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3" style="height:40px">
                    <div class="text-md-center text-xl-left">
                      <h6 class="mb-1">총 결제 금액</h6>
                    </div>
                    <div
                        class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                      <h6 class="font-weight-bold mb-0">{{this.ordersAmount}}} 원</h6>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-5 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">

                  <h4 class="card-title" style="padding-bottom: 10px;">1:1 문의내역 처리 현황 &nbsp; | &nbsp; 미답변 내역 {{ qnasWaitings }}개 </h4>

                  <div class="table-responsive">
                    <table class="table">

                      <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>답변 상태</th>
                      </tr>
                      <tbody>
                      <tr v-for="qna in qnasWaiting" :key="qna.idx" @click="goToArticle(qna.idx)">
                        <td>{{ qna.idx }}</td>
                        <td>
                          <span class="title-text">{{ qna.title }}</span>
                        </td>
                        <td>
                          <div class="badge badge-outline-danger">
                            답변 대기
                          </div>
                        </td>

                      </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-5 grid-margin stretch-card" >
              <div class="card">
                <div class="card-body"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
                  <h4 class="card-title">월별 매출 </h4>
                  <canvas ref="monthChart" id="barChart" style="height: 180px; display: block; width: 320px;" width="358" height="180" class="chartjs-render-monitor"></canvas>
                </div>
              </div>
            </div>
            <div class="col-lg-5 grid-margin stretch-card">
              <div class="card">
                <div class="card-body"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
                  <h4 class="card-title">시간대 별 로그인</h4>
                  <canvas id="areaChart" ref="loginChart" style="height: 198px; display: block; width: 396px;" width="396" height="198" class="chartjs-render-monitor"></canvas>


                </div>
              </div>
            </div>
          </div>

        </div>
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->

        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
</template>

<script>
import axios from "axios";
import { Chart, registerables } from 'chart.js'
Chart.register(...registerables)

export default {
  data() {
    return {
      visitorCount: 0, //방문자 수
      visitorcalc: 0, //방문자 수 계산

      todayOrder: 0, //결제건 수
      todayOrderCalc: 0, //결제건 수 계산

      newSignup: 0, //신규유입
      newSignupCalc: 0, //신규유입 계산

      todaySell: 0, //히루매출액
      todaySellCalc: 0, //히루매출액계산

      dormatCs: 0,
      dormatCsCalc: 0,

      qnatitle:'',

      iconClass: 'mdi-arrow-top-right', // 초기 아이콘 클래스 기본값 설정
      textClass: 'text-success', // 양수일 때 기본 텍스트 클래스 설정
      iconColorClass: 'text-success', // 양수일 때 기본 아이콘 색상 클래스 설정


      qnalist:0,


      visitorCalcClass: '', // 방문자 수 계산에 따른 클래스
      todayOrderCalcClass: '', // 결제건 수 계산에 따른 클래스
      newSignupCalcClass: '', // 신규 유입 계산에 따른 클래스
      todaySellCalcClass: '', // 하루 매출액 계산에 따른 클래스
      dormatCsCalcClass: '', // 휴면 고객 계산에 따른 클래스

      ordersCount: '',
      ordersAmount: '',

      doughnutPieData: {
        datasets: [{
          data: [],
          backgroundColor: [
            'rgba(255, 99, 132, 0.5)',
            'rgba(54, 162, 235, 0.5)',
            'rgba(255, 206, 86, 0.5)',
            'rgba(75, 192, 192, 0.5)',
            'rgba(153, 102, 255, 0.5)',
            'rgba(255, 159, 64, 0.5)'
          ],
          borderColor: [
            'rgba(255,99,132,1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
        }],

        // These labels appear in the legend and in the tooltips when hovering different arcs
        labels: [
          '패션',
          '뷰티' ,
          '가전' ,
          '식품' ,
          '스포츠/레저'
        ]
      }, doughnutPieOptions: {
        responsive: true,
        animation: {
          animateScale: true,
          animateRotate: true
        }
      },

      barData: {
        datasets: [{
          label: '월 매출액',
          data: [],
          backgroundColor: [
            'rgba(255, 99, 132, 0.5)',
            'rgba(54, 162, 235, 0.5)',
            'rgba(255, 206, 86, 0.5)',
            'rgba(75, 192, 192, 0.5)',
            'rgba(153, 102, 255, 0.5)',
            'rgba(255, 159, 64, 0.5)',
            'rgba(255, 99, 132, 0.5)',
            'rgba(54, 162, 235, 0.5)',
            'rgba(255, 206, 86, 0.5)',
            'rgba(75, 192, 192, 0.5)',
            'rgba(153, 102, 255, 0.5)',
            'rgba(255, 159, 64, 0.5)'
          ],
          borderColor: [
            'rgba(255,99,132,1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)',
            'rgba(255,99,132,1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
        }],
        // These labels appear in the legend and in the tooltips when hovering different arcs
        labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
      }, options: {
        responsive: true,
        animation: {
          animateScale: true,
          animateRotate: true
        }
      },

      areaData : {
        labels: ["00", "01","02","03","04","05", "06", "07","08","09","10","11",
          "12","13","14","15", "16","17","18","19`","20", "21","22","23"],
        datasets: [{
          label: '접속자 수',
          data: [],
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)'
          ],
          borderColor: [
            'rgba(255,99,132,1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
          borderWidth: 1,
          fill: true, // 3: no fill
        }]
      },

      areaOptions : {
        plugins: {
          filler: {
            propagate: true
          }
        },
        scales: {
          y: {
            beginAtZero:  true
          },
          yAxes: [{
            ticks: {
              min: 0.0
            },
            gridLines: {
              color: "rgba(204, 204, 204,0.1)"
            }
          }],
          xAxes: [{
            gridLines: {
              color: "rgba(204, 204, 204,0.1)"
            }
          }]
        }
      },

      qnasWaitings: 0,
      qnasWaiting: []

    };
  },


  methods: {

    updateCalcClasses() {
      this.visitorCalcClass = this.visitorcalc >= 0 ? 'text-success' : 'text-danger';
      this.todayOrderCalcClass = this.todayOrderCalc >= 0 ? 'text-success' : 'text-danger';
      this.newSignupCalcClass = this.newSignupCalc >= 0 ? 'text-success' : 'text-danger';
      this.todaySellCalcClass = this.todaySellCalc >= 0 ? 'text-success' : 'text-danger';
      this.dormatCsCalcClass = this.dormatCsCalc >= 0 ? 'text-success' : 'text-danger';
    },

    fetchVisitorCount() { //방문자 수
      axios.get('http://localhost:8000/today/login')
          .then(response => {
            this.visitorCount = response.data.todayLogin;
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchCalcCount() {
      axios.get('http://localhost:8000/today/login')
          .then(response => {
            this.visitorcalc = response.data.difLogin;
            // 데이터 업데이트 후 클래스 업데이트
            this.updateCalcClasses();
            this.iconClass = this.todayOrderCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.todayOrderCalc >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.todayOrderCalc >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },

    fetchOrders() { //결제  수
      axios.get('http://localhost:8000/today/orders')
          .then(response => {
            this.todayOrder = response.data.todayOrdersCount;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.iconClass = this.todayOrder >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.todayOrder >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.todayOrder >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchOrdersCalc() { //결제 계산
      axios.get('http://localhost:8000/today/orders')
          .then(response => {
            this.todayOrderCalc = response.data.difOrdersCount;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.updateCalcClasses();
            this.iconClass = this.todayOrderCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.todayOrderCalc >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.todayOrderCalc >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchtodaySignup() { //신규유입
      axios.get('http://localhost:8000/today/signup')
          .then(response => {
            this.newSignup = response.data.todaySignup;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.iconClass = this.newSignup >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.newSignup >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.newSignup >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchtodaySignupCalc() { //신규유입 계산
      axios.get('http://localhost:8000/today/signup')
          .then(response => {
            this.newSignupCalc = response.data.difSignup;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.updateCalcClasses();
            this.iconClass = this.newSignupCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.newSignupCalc >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.newSignupCalc >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchtodaySell() { //일 매출
      axios.get('http://localhost:8000/today/orders')
          .then(response => {
            this.todaySell = response.data.todayOrdersAmount;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.iconClass = this.todaySell >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.todaySell >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.todaySell >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchtodaySellCalc() { //일 매출 계산
      axios.get('http://localhost:8000/today/orders')
          .then(response => {
            this.todaySellCalc = response.data.difOrdersAmount;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.updateCalcClasses();
            this.iconClass = this.todaySellCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.todaySellCalc >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.todaySell >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchtdormantcs() { //휴면 고객
      axios.get('http://localhost:8000/today/sleep')
          .then(response => {
            this.dormatCs = response.data.sleepAccountGrowthRate;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.iconClass = this.dormatCs >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.dormatCs >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.dormatCs >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchtdormantcscalc() { //휴면 고객 계산
      axios.get('http://localhost:8000/today/sleep')
          .then(response => {

            this.dormatCsCalc = response.data.sleepAccountGrowthRate;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.updateCalcClasses();

            this.iconClass = this.dormatCsCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.dormatCsCalc >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.dormatCsCalc >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },


    fetchqnalist(){ //1:1문의 내역
      axios.get('http://localhost:8000/admin/qna/list')
          .then(response => {
            this.qnatitle = response.data.title;
            // this.todaySellCalc = response.data.difOrdersAmount;
            // this.todaySellCalc = response.data.difOrdersAmount;
            // this.todaySellCalc = response.data.difOrdersAmount;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },

    createChart() {
      axios.get('http://localhost:8000/category/orders')
          .then(response => {
            const responseData = response.data;
            this.doughnutPieData.datasets[0].data = responseData.orders;
            this.ordersCount = responseData.ordersCount;
            this.ordersAmount = responseData.ordersAmount;

            // 데이터 로딩이 완료된 후에 차트를 생성합니다.
            this.$nextTick(() => {
              // 차트 인스턴스가 이미 존재하는 경우, 이를 업데이트하거나 파괴 후 재생성해야 할 수도 있습니다.
              if (this.chartInstance) {
                this.chartInstance.destroy(); // 기존 차트 인스턴스를 파괴합니다.
              }
              this.chartInstance = new Chart(this.$refs.CategoryChart, {
                type: 'doughnut',
                data: this.doughnutPieData,
                options: this.doughnutPieOptions
              });
            });
          })
          .catch(error => console.error("카테고리별 판매율을 불러오는 데 실패했습니다.", error));

      axios.get('http://localhost:8000/month/orders')
          .then(response => {
            const responseData = response.data;
            this.barData.datasets[0].data = responseData.orders;
            // 데이터 로딩이 완료된 후에 차트를 생성합니다.
            this.$nextTick(() => {
              // 차트 인스턴스가 이미 존재하는 경우, 이를 업데이트하거나 파괴 후 재생성해야 할 수도 있습니다.
              if (this.chartInstance2) {
                this.chartInstance2.destroy(); // 기존 차트 인스턴스를 파괴합니다.
              }
              this.chartInstance2 = new Chart(this.$refs.monthChart, {
                type: 'bar',
                data: this.barData,
                options: this.options
              });
            });
          })
          .catch(error => console.error("월별 매출액을 불러오는 데 실패했습니다.", error));

      axios.get('http://localhost:8000/today/count')
          .then(response => {
            const responseData = response.data;
            console.log(responseData);
            this.areaData.datasets[0].data = responseData.timeDataList;
            console.log(this.areaData);
            // 데이터 로딩이 완료된 후에 차트를 생성합니다.
            this.$nextTick(() => {
              // 차트 인스턴스가 이미 존재하는 경우, 이를 업데이트하거나 파괴 후 재생성해야 할 수도 있습니다.
              if (this.chartInstance3) {
                this.chartInstance3.destroy(); // 기존 차트 인스턴스를 파괴합니다.
              }
              this.chartInstance3 = new Chart(this.$refs.loginChart, {
                type: 'line',
                data: this.areaData,

                options: this.areaOptions
              });
            });
          })
          .catch(error => console.error("시간대별 로그인을 불러오는 데 실패했습니다.", error));
    },

    loadArticles() {
      axios.get("http://localhost:8000/admin/qna/list")
          .then((response) => {
            this.qnasWaitings = response.data.filter(qna => !qna.answerContent).length;
            this.qnasWaiting = response.data.filter(qna => !qna.answerContent).slice(0, 5);
          })
          .catch((error) => {
            console.error("데이터 로드 실패:", error);
          });
    },
    goToArticle(idx) {
      this.$router.push({path: `/qnaread${idx}`});

    }

  },
  mounted() {
    this.fetchVisitorCount();
    this.fetchCalcCount();
    this.fetchOrders();
    this.fetchOrdersCalc();
    this.fetchtodaySignup();
    this.fetchtodaySignupCalc();
    this.fetchtodaySell();
    this.fetchtodaySellCalc();

    this.fetchqnalist();

    this.createChart();
    this.loadArticles();

  }

}

</script>

<style>
.text-success {
  color: #28a745; /* Green for positive numbers */
}
.text-danger {
  color: #dc3545; /* Red for negative numbers */
}
</style>
