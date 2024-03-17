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
                        <th> 쿠폰 카테고리 </th>
                        <th> 할인율 </th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr v-for="coupon in coupons" :key="coupon.idx">
                        <td>{{ coupon.idx }}</td>
                        <td>{{ getCategoryName(coupon.couponCategory) }}</td>
                        <td>{{ coupon.discount }}%</td>
<!--                        <td>{{ coupon.getHaveCouponBaseResList }}</td>-->
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
                  <h4 class="card-title">쿠폰 발급 </h4>
                  <form class="forms-sample">
                    <div class="form-group">
                      <label for="exampleSelectGender">발급대상</label>
                      <select class="form-control" id="exampleSelectGender">
                        <option>DIAMOND</option>
                        <option>PLATINUM</option>
                        <option>GOLD</option>
                        <option>SILVER</option>
                        <option>BRONZE</option>
                        <option>NEWBIE</option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="exampleSelectGender">Category</label>
                      <select class="form-control" id="exampleSelectGender">
                        <option>패션</option>
                        <option>뷰티</option>
                        <option>가전</option>
                        <option>식품</option>
                        <option>스포츠/레저</option>

                      </select>
                    </div>
                    <div class="form-group">
                      <label for="exampleInputName1">할인율</label>
                      <input type="text" class="form-control" id="exampleInputName1" placeholder="할인율">
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

export default {
  data() {
    return {
      coupons: [],
    };
  },
  methods: {
    fetchCoupon() {
      axios.get("http://localhost:8080/coupon/list")
          .then(response => {
            console.log(response);
            this.coupons = response.data;
            console.log(this.coupons); // 데이터 확인
          })
          .catch(error => {
            console.error('고객 정보를 불러오는 중 오류가 발생했습니다:', error);
          });
    },
    getCategoryName(couponCategory) {
      if (couponCategory === 1) {
        return '의류';
      } else if (couponCategory === 2) {
        return '뷰티';
      } else if (couponCategory === 3) {
        return '식품';
      } else if (couponCategory === 4) {
        return '스포츠/레저';
      } else if (couponCategory === 5) {
        return '가전';
      }
    },
    submitCoupon() {
      const categoryMapping = {
        '패션': 1,
        '뷰티': 2,
        '가전': 3,
        '식품': 4,
        '스포츠/레저': 5,
      };

      // 선택된 카테고리 값을 백엔드가 요구하는 숫자 코드로 변환
      const couponCategory = categoryMapping[this.couponData.category];

      axios.post("http://localhost:8080/coupon/create", {
        // adminIdx: 1, // 예시 값, 실제로는 적절한 값을 사용해야 합니다.
        discount: this.couponData.discount,
        couponCategory: couponCategory, // 변환된 카테고리 코드 사용
      })
          .then(response => {
            console.log(response);
            alert('쿠폰이 성공적으로 생성되었습니다.');
          })
          .catch(error => {
            console.error('쿠폰 생성 중 오류가 발생했습니다:', error);
          });
    },


  },
  mounted() {
    this.fetchCoupon();
  }
};
</script>

<style>

</style>
