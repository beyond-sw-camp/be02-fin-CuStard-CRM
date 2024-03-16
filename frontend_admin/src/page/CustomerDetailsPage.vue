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
                        <th>아이디</th>
                        <th>등급</th>
                        <th>총 구매 금액</th>
                        <th>마지막 접속일</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr class="table-info" v-for="customer in customers" :key="customer.idx">
                        <td>{{ customer.idx }}</td>
                        <td>{{ customer.customerEmail }}</td>
                        <td>{{ customer.level }}</td>
                        <td>{{ customer.totalAmount }}</td>
                        <td>{{ customer.getHaveCouponBaseResList }}</td>
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
                    <canvas id="barChart" style="height: 175px; display: block; width: 351px;" width="438" height="218"
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

export default {
  data() {
    return {
      qnasWaiting: [],
      qnasAnswered: [],
      customers: []

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
    // fetchCustomers() {
    //   axios.get("http://localhost:8080/customer/list")
    //       .then(response => {
    //         console.log(response)
    //         this.customers = response.data; // 응답으로 받은 데이터를 customers 배열에 저장
    //       })
    //       .catch(error => {
    //         console.error('고객 정보를 불러오는 중 오류가 발생했습니다:', error);
    //       });
    // },

    fetchCustomers() {
      // productIdx 변수를 사용하여 고객 정보를 불러오는 URL 수정
      axios.get(`http://localhost:8080/customer/read/${this.idx}`)
          .then(response => {
            console.log(response)
            this.customers = response.data; // 응답으로 받은 데이터를 customers 배열에 저장
          })
          .catch(error => {
            console.error('고객 정보를 불러오는 중 오류가 발생했습니다:', error);
          });
    },

    goToArticle(idx) {
      this.$router.push({path: `/admin/qna/read/${idx}`});
    }
  },
  mounted() {
    this.loadArticles();
    this.fetchCustomers();
  }
};
</script>

<style>

</style>
