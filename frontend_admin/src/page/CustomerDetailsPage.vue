<template>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper">
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row ">
            <div class="grid-margin" style="width: 95%;  padding-left: 10px;">
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
                        <td>{{ formatNumber(customer.totalAmount) }}</td>
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
            <div class="grid-margin stretch-card" style="width: 20%; padding-left: 10px;">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">1:1 문의 내역</h4>
                  <div class="table-responsive" style="overflow: hidden;">
                    <h4>답변 대기</h4>
                    <table class="table">
                      <thead>
                      <tr>
<!--                        <th>번호</th>-->
                        <th>제목</th>
                        <th>답변 상태</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr v-for="qna in qnasWaiting" :key="qna.idx" @click="goToArticle(qna.idx)">
<!--                        <td>{{ qna.idx }}</td>-->
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
<!--                        <th>번호</th>-->
                        <th>제목</th>
                        <th>답변 상태</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr v-for="qna in qnasAnswered" :key="qna.idx" @click="goToArticle(qna.idx)">
<!--                        <td>{{ qna.idx }}</td>-->
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
            </div>
            <div class="grid-margin stretch-card" style="width: 22%; padding-left: 20px" >
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">카테고리 별 구매 금액 </h4>
                    <canvas id="pieChart"
                            ref="CustomerCategoryChart"
                            style="height: 145px; display: block; width: 250px;" width="338" height="190"
                            class="chartjs-render-monitor"></canvas>
                  </div>
                </div>
              </div>
            <div class=" grid-margin stretch-card" style="width: 22%; padding-left: 10px;">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">조회 카테고리 </h4>
                    <canvas id="pieChart" ref="productReadCategory" style="height: 145px; display: block; width: 250px;" width="338" height="190"
                            class="chartjs-render-monitor"></canvas>
                  </div>
                </div>
              </div>
            <div class="grid-margin stretch-card"  style="width: 31%; padding-left: 20px;">
              <div class="card">
                <div class="card-body">
                    <h4 class="card-title">로그인 시간</h4>
                    <canvas id="areaChart" ref="customerLoginChart" style="height: 148px; display: block; width: 297px; " width="371" height="185"
                            class="chartjs-render-monitor"></canvas>
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
import axios from 'axios';
import {Chart} from "chart.js";

let backend = "http://192.168.0.33:80/api";
// let backend = "http://localhost:8080";

export default {
  data() {
    return {
      qnasWaiting: [],
      qnasAnswered: [],
      customer: [],

      ordersCategoryData: {
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
        labels: ['패션', '뷰티' , '가전' , '식품' , '스포츠/레저']
      },
      doughnutPieOptions: {
        responsive: true,
        animation: {
          animateScale: true,
          animateRotate: true
        }
      },
      productReadCategoryData: {
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
        labels: ['패션','뷰티' , '가전' , '식품' , '스포츠/레저']
      },
      areaData : {
        labels: ["00", "01","02","03","04","05", "06", "07","08","09","10","11","12","13","14","15", "16","17","18","19`","20", "21","22","23"],
        datasets: [{
          label: '방문 횟수',
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
    };
  },
  methods: {
    loadArticles() {
      axios.get(backend + "/admin/qna/list")
          .then((response) => {
            const customerId = Number(this.$route.params.customerId);
            this.qnasWaiting = response.data.result.filter(qna => qna.customerIdx === customerId && !qna.answerContent);
            this.qnasAnswered = response.data.result.filter(qna => qna.customerIdx === customerId && qna.answerContent);
          })
          .catch((error) => {
            console.error("데이터 로드 실패:", error);
          });
    },

    goToArticle(idx) {
      this.$router.push({path: `/admin/qna/read/${idx}`});
    },

    createChart() {
      axios.get(backend + `/dashboard/customer/detail/${this.$route.params.customerId}`)
          .then(response => {
            response = response.data.result;
            this.customer = response.getCustomerReadRes;

            const responseData = response.getCategoryOrdersRes;
            this.ordersCategoryData.datasets[0].data = responseData.orders;
            this.productReadCategoryData.datasets[0].data = responseData.productRead;

            this.$nextTick(() => {
              if (this.customerCategoryChartInstance) {
                this.customerCategoryChartInstance.destroy(); // 기존 차트 인스턴스를 파괴합니다.
              }

              this.customerCategoryChartInstance = new Chart(this.$refs.CustomerCategoryChart, {
                type: 'doughnut',
                data: this.ordersCategoryData,
                options: this.doughnutPieOptions
              });
            });

            this.$nextTick(() => {
              if (this.productCategoryChartInstance) {
                this.productCategoryChartInstance.destroy();
              }

              this.productCategoryChartInstance = new Chart(this.$refs.productReadCategory, {
                type: 'doughnut',
                data: this.productReadCategoryData,
                options: this.doughnutPieOptions
              });
            });
            this.areaData.datasets[0].data = response.getLoginTimeRes.timeDataList;

            this.$nextTick(() => {
              if (this.customerLoginChartInstance) {
                this.customerLoginChartInstance.destroy();
              }

              this.customerLoginChartInstance = new Chart(this.$refs.customerLoginChart, {
                type: 'line',
                data: this.areaData,
                options: this.areaOptions
              });
            });

          })
          .catch(error => console.error("카테고리별 판매율을 불러오는 데 실패했습니다.", error));
    },
    formatNumber(value) {
      return new Intl.NumberFormat().format(value);
    }
  },
  mounted() {
    this.loadArticles();
    // this.fetchCustomers();
    this.createChart();


  }
};
</script>

<style>

</style>
