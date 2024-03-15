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
                        <th> 고객 이름 </th>
                        <th> 쿠폰 카테고리 </th>
                        <th> 할인율 </th>
                        <th> 관리자 이름 </th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr>
                        <td>
                          1
                        </td>
                        <td>
                          <img src="assets/images/faces/face1.jpg" alt="image" />
                          <span class="pl-2">이창훈</span>
                        </td>
                        <td> 가전 </td>
                        <td> 50% </td>
                        <td> 김주연 </td>

                      </tr>
                      <tr>
                        <td>
                          2
                        </td>
                        <td>
                          <img src="assets/images/faces/face2.jpg" alt="image" />
                          <span class="pl-2">Estella Bryan</span>
                        </td>
                        <td> 02312 </td>
                        <td> $14,500 </td>
                        <td> Website </td>
                      </tr>
                      <tr>
                        <td>
                          3
                        </td>
                        <td>
                          <img src="assets/images/faces/face5.jpg" alt="image" />
                          <span class="pl-2">Lucy Abbott</span>
                        </td>
                        <td> 02312 </td>
                        <td> $14,500 </td>
                        <td> App design </td>
                      </tr>
                      <tr>
                        <td>
                          4
                        </td>
                        <td>
                          <img src="assets/images/faces/face3.jpg" alt="image" />
                          <span class="pl-2">Peter Gill</span>
                        </td>
                        <td> 02312 </td>
                        <td> $14,500 </td>
                        <td> Development </td>
                      </tr>
                      <tr>
                        <td>
                          5
                        </td>
                        <td>
                          <img src="assets/images/faces/face4.jpg" alt="image" />
                          <span class="pl-2">Sallie Reyes</span>
                        </td>
                        <td> 02312 </td>
                        <td> $14,500 </td>
                        <td> Website </td>
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
                      <label for="exampleSelectGender">Gender</label>
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
        <div class="content-wrapper">
          <!-- content-wrapper ends -->
          <!-- partial:partials/_footer.html -->
          <footer class="footer">
            <div class="d-sm-flex justify-content-center justify-content-sm-between">
              <span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright © bootstrapdash.com 2020</span>
              <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center"> Free <a href="https://www.bootstrapdash.com/bootstrap-admin-template/" target="_blank">Bootstrap admin templates</a> from Bootstrapdash.com</span>
            </div>
          </footer>
          <!-- partial -->
        </div>
        <!-- main-panel ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>
