<template>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper">
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row ">
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <div id="results">
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
                        <td>{{ getLevelName(customer.level) }}</td>
                        <td>{{ formatNumber(customer.totalAmount) }}원</td>
                        <td>{{ customer.lastLogin}}</td>
                      </tr>
                      </tbody>
                    </table>
                    <div class="pagination">
                    <button class="page-control" @click="changePage(-1)" :disabled="currentPage === 0"> &lt; </button>
                      <span class="page-number">{{ currentPage+1 }}</span>
                    <button class="page-control" @click="changePage(1)"> &gt; </button>
                    </div>
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
import axios from 'axios';
let backend = "http://192.168.0.33:80/api";
// let backend = "http://localhost:8000";

export default {
  data() {
    return {
      customers: [],
      originalCustomers: [],
      currentPage: 0,
      currentFilter: 'main', // 'main', 'desc', 'asc'
    };
  },
  methods: {
    fetchCustomers() {
      let url = `${backend}/es/customer/${this.currentFilter}/${this.currentPage}`;
      axios.get(url)
          .then(response => {
            this.customers = response.data.result.content;
            this.originalCustomers = [...response.data.result.content];
          })
          .catch(error => {
            console.error('고객 정보를 불러오는 중 오류가 발생했습니다:', error);
          });
    },
    changePage(increment) {
      this.currentPage += increment;
      this.fetchCustomers();
    },
    changeFilter(newFilter) {
      this.currentFilter = newFilter;
      this.currentPage = 0;
      this.fetchCustomers();
    },
    goToCustomerDetail(idx) {
      this.$router.push({ name: 'CustomerDetail', params: { customerId: idx } });
    },
    sortCustomers(type, value) {
      if (type === 'amount') {
        if (value === 'highToLow') {
          this.changeFilter('amount/desc');
        } else if (value === 'lowToHigh') {
          this.changeFilter('amount/asc');
        }
      } else if (type === 'grade' && value) {
        this.currentFilter = `level/${value}`;
        this.currentPage = 0;
        this.fetchCustomers();
      }
    },

    formatNumber(value) {
      return new Intl.NumberFormat().format(value);
    },
    getLevelName(levelIndex) {
      const levels = ['NEWBIE', 'BRONZE', 'SILVER', 'GOLD', 'PLATINUM', 'DIAMOND'];
      return levels[levelIndex - 1];
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

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 10px;
}

.page-control {
  background-color: transparent; /* 배경색 제거 */
  border: none;
  color: gray; /* 글자색 회색으로 변경 */
  padding: 5px 10px;
  margin: 0 5px;
  cursor: pointer;
}

.page-number {
  margin: 0 10px;
}




</style>
