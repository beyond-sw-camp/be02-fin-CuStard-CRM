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
            <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">방문자 수</h4>
                      <div class="d-flex align-items-center align-self-start" style="width: 250px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500;">{{ visitorCount }}명</div>
                        <div :class="['text-success' ,'ml-2', 'mb-0', 'font-weight-medium',textClass] " style="font-size: 15px; font-weight: 500; padding-left: 5px;"> {{ visitorcalc }}명 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', iconColorClass]">
                        <span :class="['mdi', 'icon-item', iconClass]"></span>
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
                      <h4 class="text-muted font-weight-normal">결제건 수</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:150px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500;">{{ todayOrder }}건</div>
                        <div :class="['text-success' ,'ml-2', 'mb-0', 'font-weight-medium',textClass] " style="font-size: 15px; font-weight: 500; padding-left: 5px;"> {{ todayOrderCalc }}건 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', iconColorClass]">
                        <span :class="['mdi', 'icon-item', iconClass]"></span>
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
                      <h4 class="text-muted font-weight-normal">신규 유입</h4>
                      <div class="d-flex align-items-center align-self-start" style="width:150px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500;">{{ newSignup }}명</div>
                        <div :class="['text-success' ,'ml-2', 'mb-0', 'font-weight-medium',textClass] " style="font-size: 15px; font-weight: 500; padding-left: 5px;"> {{ newSignupCalc }}명 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', iconColorClass]">
                        <span :class="['mdi', 'icon-item', iconClass]"></span>
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
                      <h4 class="text-muted font-weight-normal">휴면고객 재접속률</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:250px;">
                        <div style="font-size: 25px; font-weight: 500;">수정수정수정</div>
                        <div class="text-success ml-2 mb-0 font-weight-medium" style="font-size: 15px; font-weight: 500; padding-left: 5px;">
                          +5명
                        </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', iconColorClass]">
                        <span :class="['mdi', 'icon-item', iconClass]"></span>
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
                        <div :class="['text-success' ,'ml-2', 'mb-0', 'font-weight-medium',textClass] " style="font-size: 15px; font-weight: 500; padding-left: 5px;"> {{ todaySellCalc }}원 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', iconColorClass]">
                        <span :class="['mdi', 'icon-item', iconClass]"></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-6   grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="chartjs-size-monitor">
                    <div class="chartjs-size-monitor-expand">
                      <div class=""></div>
                    </div>
                    <div class="chartjs-size-monitor-shrink">
                      <div class=""></div>
                    </div>
                  </div>
                  <h4 class="card-title" style="width:280px;">카테고리 별 판매율</h4>
                  <canvas
                      id="doughnutChart"
                      style="height: 130px; display: block; width: 300px"
                      width="317"
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
                      <h6 class="font-weight-bold mb-0">842건</h6>
                    </div>
                  </div>
                  <div
                      class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3" style="height:40px">
                    <div class="text-md-center text-xl-left">
                      <h6 class="mb-1">총 결제 금액</h6>
                    </div>
                    <div
                        class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                      <h6 class="font-weight-bold mb-0">5923400원</h6>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title" style="padding-bottom: 10px;">1:1 문의내역 처리 현황 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    {{ 수정수정 }} </h4>
                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead>
                      <tr>
                        <th>User</th>
                        <th>title</th>
                        <th>Status</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr>
                        <td>Jacob</td>
                        <td>{{ qnatitle }}</td>
                        <td><label class="badge badge-danger">Pending</label></td>
                      </tr>
                      <tr>
                        <td>Messsy</td>
                        <td>상품이 고장나서 왔어요</td>
                        <td><label class="badge badge-danger">Pending</label></td>
                      </tr>
                      <tr>
                        <td>John</td>
                        <td>상한 음식이 온 것 같아요</td>
                        <td><label class="badge badge-danger">Pending</label></td>
                      </tr>
                      <tr>
                        <td>Peter</td>
                        <td>단순변심 환불도 가능할까요?</td>
                        <td><label class="badge badge-success">Completed</label></td>
                      </tr>
                      <tr>
                        <td>Dave</td>
                        <td>배송이 언제 올까요?</td>
                        <td><label class="badge badge-success">Completed</label></td>
                      </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-6 grid-margin stretch-card" >
              <div class="card">
                <div class="card-body"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
                  <h4 class="card-title">월별 매출 </h4>
                  <canvas id="barChart" style="height: 180px; display: block; width: 320px;" width="358" height="180" class="chartjs-render-monitor"></canvas>
                </div>
              </div>
            </div>
            <div class="col-lg-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
                  <h4 class="card-title">시간대 별 로그인</h4>
                  <canvas id="areaChart" style="height: 198px; display: block; width: 396px;" width="396" height="198" class="chartjs-render-monitor"></canvas>

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

export default {
  data() {
    return {
      visitorCount: 0, //방문자 수
      visitorcalc: 0, //방문자 수 계산

      todayOrder: 0, //결제건 수
      todayOrderCalc: 0, //결제건 수 계산

      newSignup: 0, //신규유입
      newSignupCalc: 0, //신규유입 계산

      todaySell:0, //히루매출액
      todaySellCalc:0, //히루매출액계산

      qnatitle:'',

      iconClass: 'mdi-arrow-top-right', // 초기 아이콘 클래스 기본값 설정
      textClass: 'text-success', // 양수일 때 기본 텍스트 클래스 설정
      iconColorClass: 'text-success', // 양수일 때 기본 아이콘 색상 클래스 설정

      qnalist:0,
    };
  },
  methods: {
    fetchVisitorCount() { //방문자 수
      axios.get('http://localhost:8000/today/login')
          .then(response => {
            this.visitorCount = response.data.todayLogin;
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchCalcCount(){ //방문자 수
      axios.get('http://localhost:8000/today/login')
          .then(response => {
            this.visitorcalc = response.data.difLogin;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.iconClass = this.visitorcalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.visitorcalc >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.visitorcalc >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchOrders(){ //결제  수
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
    fetchOrdersCalc(){ //결제 계산
      axios.get('http://localhost:8000/today/orders')
          .then(response => {
            this.todayOrderCalc = response.data.difOrdersCount;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.iconClass = this.todayOrderCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.todayOrderCalc >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.todayOrderCalc >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchtodaySignup(){ //신규유입
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
    fetchtodaySignupCalc(){ //신규유입 계산
      axios.get('http://localhost:8000/today/signup')
          .then(response => {
            this.newSignupCalc = response.data.difSignup;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.iconClass = this.newSignupCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.newSignupCalc >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.newSignupCalc >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchtodaySell(){ //일 매출
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
    fetchtodaySellCalc(){ //일 매출 계산
      axios.get('http://localhost:8000/today/orders')
          .then(response => {
            this.todaySellCalc = response.data.difOrdersAmount;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.iconClass = this.todaySellCalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.todaySellCalc >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.todaySell >= 0 ? 'text-success' : 'text-danger';
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
  }
}

</script>

<style>
.text-danger {
  color: red;
}

</style>
