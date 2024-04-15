<template>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper">
      <div class="main-panel">
        <div class="content-wrapper">
          <h6 class="text-muted font-weight-normal" style="padding-left:10px;"> 전일 동시간대 기준</h6>
          <div class="row">
            <div class="grid-margin stretch-card" style="width: 17%; height: 110px;">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-8">
                      <h4 class="text-muted font-weight-normal">방문자 수</h4>
                      <div class="d-flex align-items-center align-self-start" style="width: 300px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500; padding-right: 10px;">{{ getTodayLoginRes.todayLogin }}명</div>
                        <div :class="['ml-2', 'mb-0', 'font-weight-medium', getTodayLoginRes.difLogin >= 0 ? 'text-success' : 'text-danger']">
                          {{ getTodayLoginRes.difLogin }}명
                        </div>

                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', getTodayLoginRes.difLogin >= 0 ? 'icon-box-success' : 'icon-box-danger']">
                        <div :class="['icon', getTodayLoginRes.difLogin >= 0 ? 'text-success' : 'text-danger']">
                          <span :class="['mdi', 'icon-item', getTodayLoginRes.difLogin >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left']"></span>
                        </div>
                    </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="grid-margin stretch-card" style="width: 17%; height: 110px; padding-left: 10px;">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">결제건 수</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:150px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500; padding-right: 10px;">{{ getTodayOrdersRes.todayOrdersCount }}건</div>
                        <div :class="['ml-2', 'mb-0', 'font-weight-medium', getTodayOrdersRes.difOrdersCount >= 0 ? 'text-success' : 'text-danger'] " > {{ getTodayOrdersRes.difOrdersCount }}건 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', getTodayOrdersRes.difOrdersCount >= 0 ? 'icon-box-success' : 'icon-box-danger']">
                        <div :class="['icon', getTodayOrdersRes.difOrdersCount >= 0 ? 'text-success' : 'text-danger']">
                          <span :class="['mdi', 'icon-item', getTodayOrdersRes.difOrdersCount >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left']"></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="grid-margin stretch-card" style="width: 17%; height: 110px; padding-left: 10px;">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">신규 유입</h4>
                      <div class="d-flex align-items-center align-self-start" style="width:150px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500; padding-right: 10px;">{{ getTodaySignupRes.todaySignup }}명</div>
                        <div :class="['ml-2', 'mb-0', 'font-weight-medium', getTodaySignupRes.difSignup >= 0 ? 'text-success' : 'text-danger'] "> {{ getTodaySignupRes.difSignup }}명 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', getTodaySignupRes.difSignup >= 0 ? 'icon-box-success' : 'icon-box-danger']">
                        <div :class="['icon', getTodaySignupRes.difSignup >= 0 ? 'text-success' : 'text-danger']">
                          <span :class="['mdi', 'icon-item', getTodaySignupRes.difSignup >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left']"></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="grid-margin stretch-card" style="width: 20%; height: 110px; padding-left: 10px;">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">

                      <h4 class="text-muted font-weight-normal">휴면고객 재접속</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:250px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500; padding-right: 10px;">{{ getSleepAccountRes.todaySleepAccount }}명</div>
                        <div :class="['ml-2', 'mb-0', 'font-weight-medium',getSleepAccountRes.difSleepAccount >= 0 ? 'text-success' : 'text-danger' ] "> {{ getSleepAccountRes.difSleepAccount }}명 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', getSleepAccountRes.difSleepAccount >= 0 ? 'icon-box-success' : 'icon-box-danger']">
                        <div :class="['icon', getSleepAccountRes.difSleepAccount >= 0 ? 'text-success' : 'text-danger']">
                          <span :class="['mdi', 'icon-item', getSleepAccountRes.difSleepAccount >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left']"></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="grid-margin stretch-card" style="width: 23%; height: 110px; padding-left: 10px;">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">하루 매출액</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:250px;">
                        <div class="font-weight-medium" style="font-size: 25px; font-weight: 500; padding-right: 10px;">{{ formatNumber(getTodayOrdersRes.todayOrdersAmount) }}원</div>
                        <div :class="['ml-2', 'mb-0', 'font-weight-medium', getTodayOrdersRes.difOrdersAmount  >= 0 ? 'text-success' : 'text-danger' ]"> {{ formatNumber(getTodayOrdersRes.difOrdersAmount) }}원 </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div :class="['icon', getTodayOrdersRes.difOrdersAmount >= 0 ? 'icon-box-success' : 'icon-box-danger']">
                        <div :class="['icon', getTodayOrdersRes.difOrdersAmount >= 0 ? 'text-success' : 'text-danger']">
                          <span :class="['mdi', 'icon-item', getTodayOrdersRes.difOrdersAmount >= 0 ? 'mdi-arrow-top-right' : 'mdi-arrow-bottom-left']"></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="grid-margin stretch-card" style="width: 47%;">
              <div class="card">
                <div class="card-body"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
                  <h4 class="card-title">월별 매출 </h4>
                  <canvas ref="monthChart" id="barChart" style="height: 140px; display: block; width: 320px;" width="358" height="140" class="chartjs-render-monitor"></canvas>
                </div>
              </div>
            </div>
            <div class="grid-margin stretch-card" style="width: 47%; padding-left: 20px;">
              <div class="card">
                <div class="card-body"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
                  <h4 class="card-title">시간대 별 로그인</h4>
                  <canvas id="areaChart" ref="loginChart" style="height: 150px; display: block; width: 396px;" width="396" height="150" class="chartjs-render-monitor"></canvas>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="grid-margin stretch-card" style="width: 30%;">
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
                      style="height: 130px; display: block; width: 400px"
                      width="440"
                      height="130"
                      class="chartjs-render-monitor"
                  ></canvas>
                </div>
              </div>
            </div>
            <div class="grid-margin stretch-card" style="width: 24%;">
              <div class="card" style="padding-left: 40px;" >
                <div class="card-body">
                    <div class="text-md-center text-xl-left" style="padding-top: 45%;">
                      <div style="display: flex;">
                        <h6 class="mb-1" style="font-size: large; font-weight: 100; color: #d99100;" >결제 수</h6>
                        <h5 style="color: rgb(128,128,128); font-size: small; padding-left: 10px; padding-top: 3px;"> (지난 14일 기준) </h5>
                      </div>
                      <h6 class="font-weight-bold mb-0"  style="padding-top: 5%; font-size: x-large; color: #FFFFFF; display: flex;">{{getCategoryOrdersRes.ordersCount}} 건</h6>

                  </div>
                  <div style="padding-top: 20%;">
                    <div style="display: flex;">
                      <h6 class="mb-1" style="font-size: large; font-weight: 100; color: #d99100;">총 결제 금액</h6>
                      <h5 style="color: rgb(128,128,128); font-size: small; padding-left: 10px; padding-top: 3px;"> (지난 14일 기준) </h5>
                    </div>
                    <h6 class="font-weight-bold mb-0"  style="padding-top: 5%; font-size: x-large;  color: #FFFFFF">{{formatNumber(getCategoryOrdersRes.ordersAmount)}} 원</h6>
                </div>
                    </div>
              </div>
            </div>
            <div class="grid-margin stretch-card" style="width: 40%; padding-left: 1%;">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title" style="padding-bottom: 10px; display: flex;">1:1 문의내역 처리 현황 &nbsp; | &nbsp; 미답변 내역 <div style="color: #dc3545; padding-left: 10px;"> {{ qnasWaitings }}</div> &nbsp; 개  </h4>
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
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Chart, registerables } from 'chart.js'
Chart.register(...registerables)

let backend = "http://192.168.0.33:80/api";
// let backend = "http://localhost:8000";

export default {
  data() {
    return {
      getTodayLoginRes: {todayLogin: 0, difLogin: 0},
      getTodaySignupRes: {todaySignup: 0, difSignup: 0},
      getTodayOrdersRes: {todayOrdersCount: 0, difOrdersCount: 0,todayOrdersAmount: 0, difOrdersAmount: 0},
      getCategoryOrdersRes: {orders:[null], productRead:[null] ,ordersCount: 0, ordersAmount: 0},
      getMonthOrdersRes: null,
      getSleepAccountRes: {todaySleepAccount: 0, difSleepAccount: 0},
      getLoginTimeRes: {timeDataList: [null]},

      iconClass: 'mdi-arrow-top-right',
      textClass: 'text-success',
      iconColorClass: 'text-success',

      qnatitle:'',
      qnalist:0,
      qnasWaitings: 0,
      qnasWaiting: [],

      doughnutPieData: {
        datasets: [{
          data: [],
          backgroundColor: [
            'rgba(255, 99, 132, 0.5)', 'rgba(54, 162, 235, 0.5)', 'rgba(255, 206, 86, 0.5)', 'rgba(75, 192, 192, 0.5)', 'rgba(153, 102, 255, 0.5)', 'rgba(255, 159, 64, 0.5)'],
          borderColor: [
            'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'],
        }],

        // These labels appear in the legend and in the tooltips when hovering different arcs
        labels: ['패션', '뷰티' , '가전' , '식품' , '스포츠/레저']
      },
      barData: {
        datasets: [{
          label: '월 매출액',
          data: [],
          backgroundColor: [
            'rgba(255, 99, 132, 0.5)', 'rgba(54, 162, 235, 0.5)', 'rgba(255, 206, 86, 0.5)', 'rgba(75, 192, 192, 0.5)', 'rgba(153, 102, 255, 0.5)', 'rgba(255, 159, 64, 0.5)', 'rgba(255, 99, 132, 0.5)',
            'rgba(54, 162, 235, 0.5)', 'rgba(255, 206, 86, 0.5)', 'rgba(75, 192, 192, 0.5)', 'rgba(153, 102, 255, 0.5)', 'rgba(255, 159, 64, 0.5)'
          ],
          borderColor: [
            'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)', 'rgba(255,99,132,1)',
            'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'],
        }],
        // These labels appear in the legend and in the tooltips when hovering different arcs
        labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
      },
      options: {
        responsive: true,
        animation: {
          animateScale: true,
          animateRotate: true
        }
      },
      areaData : {
        labels: ["00", "01","02","03","04","05", "06", "07","08","09","10","11", "12","13","14","15", "16","17","18","19`","20", "21","22","23"],
        datasets: [{
          label: '접속자 수',
          data: [],
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'],
          borderColor: [
            'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'],
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
    };
  },


  methods: {
    createChart() {
      axios.get(backend + '/es/dashboard/main')
          .then(response => {
            const result = response.data.result;

            this.getTodayLoginRes = result.getTodayLoginRes;
            this.getTodaySignupRes = result.getTodaySignupRes;
            this.getTodayOrdersRes = result.getTodayOrdersRes;
            this.getCategoryOrdersRes = result.getCategoryOrdersRes;
            this.getMonthOrdersRes = result.getMonthOrdersRes;
            this.getSleepAccountRes= result.getSleepAccountRes;
            this.getLoginTimeRes = result.getLoginTimeRes;

            //카테고리별 판매율 차트 그리기
            this.doughnutPieData.datasets[0].data = this.getCategoryOrdersRes.orders;

            this.$nextTick(() => {
              if (this.chartInstance) {
                this.chartInstance.destroy();
              }
              this.chartInstance = new Chart(this.$refs.CategoryChart, {
                type: 'doughnut',
                data: this.doughnutPieData,
                options: this.options,
              });
            });

            //월별 매출 차트 그리기
            this.barData.datasets[0].data = this.getMonthOrdersRes.orders;
            this.$nextTick(() => {
              if (this.chartInstance2) {this.chartInstance2.destroy();}

              this.chartInstance2 = new Chart(this.$refs.monthChart, {
                type: 'bar',
                data: this.barData,
                options: this.options
              });
            });

            //시간대 별 로그인 차트 그리기
            this.areaData.datasets[0].data = this.getLoginTimeRes.timeDataList;
            this.$nextTick(() => {
              if (this.chartInstance3) {this.chartInstance3.destroy();}

              this.chartInstance3 = new Chart(this.$refs.loginChart, {
                type: 'line',
                data: this.areaData,
                options: this.areaOptions
              });
            });
          })
    },

    fetchqnalist(){ //1:1문의 내역
      axios.get(backend + '/admin/qna/list')
          .then(response => {
            this.qnatitle = response.data.result.title;
          })
          .catch(error => console.error("방문자 수를 불러오는 데 실패했습니다.", error));
    },
    loadArticles() {
      axios.get(backend + "/admin/qna/list")
          .then((response) => {
            this.qnasWaitings = response.data.result.filter(qna => !qna.answerContent).length;
            this.qnasWaiting = response.data.result.filter(qna => !qna.answerContent).slice(0, 5);
          })
          .catch((error) => {
            console.error("데이터 로드 실패:", error);
          });
    },
    goToArticle(idx) {
      this.$router.push({path: `/qnaread${idx}`});
    },
    formatNumber(value) {
      return new Intl.NumberFormat().format(value);
    }
  },
  mounted() {
    this.createChart();
    this.fetchqnalist();
    this.loadArticles();
  }

}
</script>

<style>
.icon.icon-box-success {
  width: 40px;
  height: 37px;
  background: rgba(0, 210, 91, 0.11);
  border-radius: 7px;
  color: #00d25b;
}
.icon.icon-box-danger {
  width: 40px;
  height: 37px;
  background: rgba(252, 66, 74, 0.11);
  border-radius: 7px;
  color: #fc424a;
}

.text-success {
  color: #28a745; /* Green for positive numbers */
}
.text-danger {
  color: #dc3545; /* Red for negative numbers */
}
</style>
