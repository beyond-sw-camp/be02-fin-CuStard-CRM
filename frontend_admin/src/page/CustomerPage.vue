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

                  <div id="results">
                    <!-- 정렬 및 필터링된 결과가 여기에 표시됩니다 -->
                  </div>
                  <div class="select">
                    <h4 class="card-title csInfo">고객 정보</h4>
                    <div class="filter-container">
                      <select id="amountFilter" class="btn btn-outline-secondary dropdown-toggle filter" @change="sortCustomers('amount', $event.target.value)">
                        <option value="">주문금액순 선택</option>
                        <option value="highToLow">높은 금액순</option>
                        <option value="lowToHigh">낮은 금액순</option>
                      </select>
                      <select id="gradeFilter" class="filter btn btn-outline-secondary dropdown-toggle" @change="sortCustomers('grade', $event.target.value)">
                        <option value="">등급별 선택</option>
                        <option value="DIAMOND">DIAMOND</option>
                        <option value="PLATINUM">PLATINUM</option>
                        <option value="GOLD">GOLD</option>
                        <option value="SILVER">SILVER</option>
                        <option value="BRONZE">BRONZE</option>
                        <option value="NEWBIE">NEWBIE</option>

                      </select>

                    </div>
                  </div>

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
let backend = "http://192.168.0.33:80/api";
// let backend = "http://localhost:8000";

export default {
  data() {
    return {
      customers: [],
      originalCustomers: [], // 원본 고객 목록을 저장할 속성

    };
  },
  methods: {
    fetchCustomers() {
      axios.get(backend + "/admin/customer/list") //:Todo 백엔드 어드민으로 분리할 것
          .then(response => {
            this.customers = response.data.result;
            this.originalCustomers = [...response.data.result];
          })
          .catch(error => {
            console.error('고객 정보를 불러오는 중 오류가 발생했습니다:', error);
          });
    },
    goToCustomerDetail(idx) {
      this.$router.push({ name: 'CustomerDetail', params: { customerId: idx } });
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
    formatNumber(value) {
      return new Intl.NumberFormat().format(value);
    },
  },
  mounted() {
    this.fetchCustomers();
  }
};
</script>

<style>

.select{
  display: flex;
}
.csInfo{
  margin-right: 25px;
  margin-top: 7px;
}
select{
  padding: 2px;
  margin-right: 10px;
}
select > option{
  text-align: left;
}
</style>
