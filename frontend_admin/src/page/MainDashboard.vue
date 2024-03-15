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
                      <h4 class="text-muted font-weight-normal">신규 유입</h4>
                      <div class="d-flex align-items-center align-self-start" style="width:150px;">
                        <div style="font-size: 25px; font-weight: 500;">12명</div>
                        <div class="text-danger ml-2 mb-0 font-weight-medium" style="font-size: 15px; font-weight: 500; padding-left: 5px;">
                          -3명
                        </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div class="icon icon-box-danger">
                          <span
                              class="mdi mdi-arrow-bottom-left icon-item"
                          ></span>
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
                        <div style="font-size: 25px; font-weight: 500;">19명</div>
                        <div class="text-success ml-2 mb-0 font-weight-medium" style="font-size: 15px; font-weight: 500; padding-left: 5px;">
                          +5명
                        </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div class="icon icon-box-success">
                          <span
                              class="mdi mdi-arrow-top-right icon-item"
                          ></span>
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
                        <div style="font-size: 25px; font-weight: 500;">384778원</div>
                        <div class="text-success ml-2 mb-0 font-weight-medium" style="font-size: 15px; font-weight: 500; padding-left: 5px;">
                          +2400원
                        </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div class="icon icon-box-success">
                          <span
                              class="mdi mdi-arrow-top-right icon-item"
                          ></span>
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
                  <h4 class="card-title" style="padding-bottom: 10px;">1:1 문의내역 처리 현황 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 미답변 내역 3개 </h4>
                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead>
                      <tr>
                        <th>User</th>
                        <th>Title</th>
                        <th>Status</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr>
                        <td>Jacob</td>
                        <td>배송이 7일째 안옵니다..</td>
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
      visitorcalc: 0, //방문자 수 일별
      iconClass: 'mdi-arrow-top-right', // 초기 아이콘 클래스 기본값 설정
      textClass: 'text-success', // 양수일 때 기본 텍스트 클래스 설정
      iconColorClass: 'text-success' // 양수일 때 기본 아이콘 색상 클래스 설정
    };
  },
  methods: {
    fetchVisitorCount() {
      axios.get('http://localhost:8000/today/login')
          .then(response => {
            this.visitorCount = response.data.todayLogin;
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    fetchCalcCount(){
      axios.get('http://localhost:8000/today/login')
          .then(response => {
            this.visitorcalc = response.data.difLogin;
            // visitorcalc 값에 따라 아이콘 클래스와 색상 클래스 동적 업데이트
            this.iconClass = this.visitorcalc >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left';
            this.iconColorClass = this.visitorcalc >= 0 ? 'text-success' : 'text-danger';
            this.textClass = this.visitorcalc >= 0 ? 'text-success' : 'text-danger';
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    }
  },
  mounted() {
    this.fetchVisitorCount();
    this.fetchCalcCount();
  }
}

</script>

<style>
.text-danger {
  color: red;
}

</style>
