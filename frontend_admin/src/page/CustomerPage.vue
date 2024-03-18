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
                  <div class="filter-container">
                    <select id="amountFilter" class="filter" @change="sortCustomers('amount', $event.target.value)">
                      <option value="">주문금액순 선택</option>
                      <option value="highToLow">높은 금액순</option>
                      <option value="lowToHigh">낮은 금액순</option>
                    </select>
                    <select id="gradeFilter" class="filter" @change="sortCustomers('grade', $event.target.value)">
                      <option value="">등급별 선택</option>
                      <option value="DIAMOND">DIAMOND</option>
                      <option value="PLATINUM">PLATINUM</option>
                      <option value="GOLD">GOLD</option>
                      <option value="SILVER">SILVER</option>
                      <option value="BRONZE">BRONZE</option>
                      <option value="NEWBIE">NEWBIE</option>

                    </select>

                  </div>
                  <div id="results">
                    <!-- 정렬 및 필터링된 결과가 여기에 표시됩니다 -->
                  </div>
                  <h4 class="card-title">고객 정보</h4>
                  <div class="table-responsive">
                    <table class="table table-bordered table-contextual">
                      <thead>
                      <tr>
                        <th>번호</th>
                        <th>이름</th>
                        <th>이메일</th>
                        <th>등급</th>
                        <th>총 구매 금액</th>
                        <th>마지막 접속일</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr class="table-info" v-for="customer in customers" :key="customer.idx" @click="goToCustomerDetail(customer.idx)">
                        <td>{{ customer.idx }}</td>
                        <td>{{ customer.name }}</td>
                        <td>{{ customer.customerEmail }}</td>
                        <td>{{ customer.level }}</td>
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
          <!-- content-wrapper ends -->
          <!-- partial:partials/_footer.html -->
          <!-- partial -->
        </div>
        <!-- main-panel ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      qnasWaiting: [],
      qnasAnswered: [],
      customers: [],
      originalCustomers: [], // 원본 고객 목록을 저장할 속성

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
      axios.get("http://localhost:8080/customer/list")
          .then(response => {
            console.log(response);
            this.customers = response.data;
            this.originalCustomers = [...response.data];
            console.log(this.customers); // 데이터 확인
          })
          .catch(error => {
            console.error('고객 정보를 불러오는 중 오류가 발생했습니다:', error);
          });
    },
    goToCustomerDetail(idx) {
      this.$router.push({ name: 'CustomerDetail', params: { customerId: idx } });
    },



    goToArticle(idx) {
      this.$router.push({path: `/admin/qna/read/${idx}`});
    },

    sortCustomers(type, value) {
      this.customers = [...this.originalCustomers]; // 원본 데이터로 리셋

      console.log('Filtering by:', type, value);

      if (type === 'amount') {
        if (value === 'highToLow') {
          this.customers.sort((a, b) => b.totalAmount - a.totalAmount);
        } else if (value === 'lowToHigh') {
          this.customers.sort((a, b) => a.totalAmount - b.totalAmount);
        }
      } else if (type === 'grade' && value) {
        this.customers = this.customers.filter(customer => customer.level === value);
      }

      console.log('Filtered customers:', this.customers);
    },




  },
  mounted() {
    this.loadArticles();
    this.fetchCustomers();
  }
};
</script>

<style>

</style>
