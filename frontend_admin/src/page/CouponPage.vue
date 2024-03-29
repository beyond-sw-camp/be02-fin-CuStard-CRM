<template>
  <div class="container-scroller">
    <!-- partial:partials/_sidebar.html -->
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_navbar.html -->
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div id="results">
                    <!-- 정렬 및 필터링된 결과가 여기에 표시됩니다 -->
                  </div>
                  <h4 class="card-title">쿠폰 목록</h4>
                  <div class="table-responsive">
                    <table class="table">
                      <thead>
                      <tr>
                        <th>번호</th>
                        <th> 쿠폰 카테고리</th>
                        <th> 할인율</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr v-for="coupon in coupons" :key="coupon.idx">
                        <td>{{ coupon.idx }}</td>
                        <td>{{ getCategoryName(coupon.couponCategory) }}</td>
                        <td>{{ coupon.discount }}%</td>
                      </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">쿠폰 발급</h4>
                  <!-- 동적으로 변경되는 폼 -->
                  <form class="forms-sample" @submit.prevent="submitCoupon">
                    <div class="form-group">
                      <label for="selectionOption">발급 대상 선택</label>
                      <select class="form-control" id="selectionOption" v-model="selectedOption"
                              @change="updateCategoryOptions">
                        <option value="">발급 옵션을 선택하세요</option>
                        <option value="grade">고객 등급</option>
                        <option value="money">누적 구매 금액</option>
                        <option value="sleeper">휴면 기간</option>
                      </select>
                    </div>
                    <!-- 발급 대상에 따라 동적으로 나타나는 폼 -->
                    <div v-if="selectedOption === 'grade'" class="form-group">
                      <label for="selectGrade">고객 등급</label>
                      <select class="form-control" id="selectGrade" v-model="couponData.customerGrade">
                        <option value="DIAMOND">다이아몬드</option>
                        <option value="PLATINUM">플래티넘</option>
                        <option value="GOLD">골드</option>
                        <option value="SILVER">실버</option>
                        <option value="BRONZE">브론즈</option>
                        <option value="NEWBIE">신규고객</option>
                      </select>
                    </div>
                    <div v-if="selectedOption === 'money'" class="form-group">
                      <label for="selectPurchase">누적 구매 금액</label>
                      <input type="text" class="form-control" id="selectMoney" v-model="couponData.purchaseAmount"
                             placeholder="누적 구매 금액">
                    </div>
                    <div v-if="selectedOption === 'sleeper'" class="form-group">
                      <label for="selectPurchase">휴면 기간</label>
                      <input type="text" class="form-control" id="selectSleeper" v-model="couponData.dormantPeriod"
                             placeholder="휴면 기간">
                    </div>
                    <div class="form-group">
                      <label for="selectCategory">Category</label>
                      <select class="form-control" id="selectCategory" v-model="couponData.category">
                        <option value="">카테고리 선택</option>
                        <option>패션</option>
                        <option>뷰티</option>
                        <option>가전</option>
                        <option>식품</option>
                        <option>스포츠/레저</option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="exampleInputName1">할인율</label>
                      <input type="text" class="form-control" id="exampleInputName1" placeholder="할인율"
                             v-model="couponData.discount">
                    </div>
                    <button type="submit" class="btn btn-primary mr-2">Submit</button>
                    <button class="btn btn-dark">Cancel</button>
                  </form>
                </div>
              </div>
            </div>
          </div>
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
export default {
  data() {
    return {
      coupons: [],
      selectedOption: '',
      couponData: {
        customerGrade: '',
        purchaseAmount: '',
        dormantPeriod: '',
        category: '',
        discount: ''
      }
    };
  },
  methods: {
    fetchCoupon() {
      axios.get(backend + "/admin/coupon/list")
          .then(response => {
            this.coupons = response.data.result;
          })
          .catch(error => {
            console.error('고객 정보를 불러오는 중 오류가 발생했습니다:', error);
          });
    },
    getCategoryName(couponCategory) {
      const categoryMapping = {
        1: '의류',
        2: '뷰티',
        3: '식품',
        4: '스포츠/레저',
        5: '가전'
      };
      return categoryMapping[couponCategory];
    },
    submitCoupon() {
      const categoryMapping = {
        '패션': 1,
        '뷰티': 2,
        '가전': 3,
        '식품': 4,
        '스포츠/레저': 5
      };

      const couponCategory = categoryMapping[this.couponData.category];
      let dataToSend;
      if (this.selectedOption === 'grade') {
        dataToSend = this.couponData.customerGrade;
      } else if (this.selectedOption === 'money') {
        dataToSend= this.couponData.purchaseAmount;
      } else if (this.selectedOption === 'sleeper') {
        dataToSend = this.couponData.dormantPeriod;
      } else {
        console.error('유효하지 않은 옵션입니다.');
        return;
      }
      axios.post(backend + "/admin/coupon/create", {
        selectedOption: this.selectedOption,
        dataToSend: dataToSend,
        discount: this.couponData.discount,
        couponCategory: couponCategory
      },{
        headers:{
          Authorization: localStorage.getItem("accessToken")
        }
      }).then(response => {
        console.log(response);
        alert('쿠폰이 성공적으로 생성되었습니다.');
      })
          .catch(error => {
            console.error('쿠폰 생성 중 오류가 발생했습니다:', error);
          });
    },
    updateCategoryOptions() {
      // Reset couponData when the selection changes
      this.couponData = {
        customerGrade: '',
        purchaseAmount: '',
        dormantPeriod: '',
        category: '',
        discount: ''
      };
    }
  },
  mounted() {
    this.fetchCoupon();
  }
};
</script>

<style>
</style>