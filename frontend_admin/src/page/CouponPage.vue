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
    getCategoryName(category) {
      if (category === '1') {
        return '의류';
      } else if (category === '2') {
        return '뷰티';
      } else if (category === '3') {
        return '식품';
      } else if (category === '4') {
        return '스포츠/레저';
      } else if (category === '5') {
        return '가전';
      } else {
        return '기타'; // 기타 카테고리를 처리하기 위한 기본 반환값
      }
    },


  },
  mounted() {
    this.fetchCoupon();
  }
};
</script>

<style>

</style>
