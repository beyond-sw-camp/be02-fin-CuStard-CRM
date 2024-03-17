<template>
  <div class="container-scroller">
    <!-- partial:partials/_sidebar.html -->
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_navbar.html -->
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row ">
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">고객 정보</h4>
                  <div class="table-responsive">
                    <table class="table table-bordered table-contextual">
                      <thead>
                      <tr>
                        <th>번호</th>
                        <th>이름</th>
                        <th>이메일</th>
                        <th>등급</th>
                        <th>나이</th>
                        <th>성별</th>
                        <th>배송지</th>
                        <th>총 구매 금액</th>
                        <th>마지막 접속일</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr class="table-info">
                        <td>{{ customer.idx }}</td>
                        <td>{{ customer.name }}</td>
                        <td>{{ customer.customerEmail }}</td>
                        <td>{{ customer.level }}</td>
                        <td>{{ customer.age }}</td>
                        <td>{{ customer.gender }}</td>
                        <td>{{ customer.address }}</td>
                        <td>{{ customer.totalAmount }}</td>
                        <td>{{ customer.lastLogin}}</td>
                      </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-4 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">1:1 문의 내역</h4>
                  <div class="table-responsive" style="overflow: hidden;">
                    <h4>답변 대기</h4>
                    <table class="table">
                      <thead>
                      <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>답변 상태</th>
                      </tr>
                      </thead>
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
                    <h4>답변 완료</h4>
                    <table class="table">
                      <thead>
                      <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>답변 상태</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr v-for="qna in qnasAnswered" :key="qna.idx" @click="goToArticle(qna.idx)">
                        <td>{{ qna.idx }}</td>
                        <td>
                          <span class="title-text">{{ qna.title }}</span>
                        </td>
                        <td>
                          <div class="badge badge-outline-success">
                            답변 완료
                          </div>
                        </td>
                      </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              <div class="col-md-8 grid-margin stretch-card">
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
                    <h4 class="card-title">로그인 시간</h4>
                    <canvas id="areaChart" style="height: 148px; display: block; width: 297px;" width="371" height="185"
                            class="chartjs-render-monitor"></canvas>

                  </div>

                </div>
              </div>
            </div>


            <div class="row">
              <div class="col-lg-6 grid-margin stretch-card">
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
                    <h4 class="card-title">카테고리 별 구매 금액 </h4>
                    <canvas id="barChart"
                            ref="CustomerCategoryChart"
                            style="height: 175px; display: block; width: 351px;" width="438" height="218"
                            class="chartjs-render-monitor"></canvas>
                  </div>
                </div>
              </div>
              <div class="col-lg-6 grid-margin stretch-card">
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
                    <h4 class="card-title">조회 카테고리 </h4>
                    <canvas id="pieChart" style="height: 185px; display: block; width: 371px;" width="463" height="231"
                            class="chartjs-render-monitor"></canvas>
                  </div>
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
import axios from 'axios';
import {Chart} from "chart.js";

export default {
  data() {
    return {
      qnasWaiting: [],
      qnasAnswered: [],
      customer: [],

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

    };
  },
  methods: {
    loadArticles() {
      axios.get("http://localhost:8000/admin/qna/list")
          .then((response) => { //:TODO customerIdx를 받아온 값으로 변경해야함
            console.log(response);
            this.qnasWaiting = response.data.filter(qna => qna.customerIdx === 7 && !qna.answerContent);
            this.qnasAnswered = response.data.filter(qna => qna.customerIdx === 7 && qna.answerContent);
            console.log(this.qnasWaiting);
            console.log(this.qnasAnswered);
          })
          .catch((error) => {
            console.error("데이터 로드 실패:", error);
          });
    },

    fetchCustomers() {
      // productIdx 변수를 사용하여 고객 정보를 불러오는 URL 수정
      axios.get(`http://localhost:8080/customer/read/${this.$route.params.customerId}`)
          .then(response => {
            // console.log(response)
            this.customer = response.data; // 응답으로 받은 데이터를 customers 배열에 저장
            console.log(this.customer)
          })
          .catch(error => {
            console.error('고객 정보를 불러오는 중 오류가 발생했습니다:', error);
          });
    },

    goToArticle(idx) {
      this.$router.push({path: `/admin/qna/read/${idx}`});
    },

    createChart() {
      axios.get(`http://localhost:8000/customer/orders/${this.$route.params.customerId}`)
          .then(response => {
            const responseData = response.data;
            this.doughnutPieData.datasets[0].data = responseData.array;
            // 데이터 로딩이 완료된 후에 차트를 생성합니다.
            this.$nextTick(() => {
              // 차트 인스턴스가 이미 존재하는 경우, 이를 업데이트하거나 파괴 후 재생성해야 할 수도 있습니다.
              if (this.chartInstance) {
                this.customerCategoryChartInstance.destroy(); // 기존 차트 인스턴스를 파괴합니다.
              }
              this.customerCategoryChartInstance = new Chart(this.$refs.CustomerCategoryChart, {
                type: 'doughnut',
                data: this.doughnutPieData,
                options: this.doughnutPieOptions
              });
            });
          })
          .catch(error => console.error("카테고리별 판매율을 불러오는 데 실패했습니다.", error));
    },
  },
  mounted() {
    this.loadArticles();
    this.fetchCustomers();
    this.createChart();
  }
};
</script>

<style>

</style>
