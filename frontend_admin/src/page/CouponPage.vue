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
            <div class="row ">
              <div class="col-12 grid-margin">
                <div class="card">
                  <div class="card-body">
                    <div class="filter-container">
                      <select id="amountFilter" class="filter">
                        <option value="">주문금액순 선택</option>
                        <option value="highToLow">높은 금액순</option>
                        <option value="lowToHigh">낮은 금액순</option>
                      </select>
                      <select id="gradeFilter" class="filter">
                        <option value="">등급별 선택</option>
                        <option value="vip">VIP</option>
                        <option value="premium">프리미엄</option>
                        <option value="regular">일반</option>
                      </select>
                    </div>
                    <div id="results">
                      <!-- 정렬 및 필터링된 결과가 여기에 표시됩니다 -->
                    </div>
                    <h4 class="card-title">고객 목록</h4>
                    <div class="table-responsive">
                      <table class="table">
                        <thead>
                        <tr>
                          <th>
                            <div class="form-check form-check-muted m-0">
                              <label class="form-check-label">
                                <input type="checkbox" class="form-check-input">
                              </label>
                            </div>
                          </th>
                          <th> 고객 이름 </th>
                          <th> 이메일 </th>
                          <th> 등급 </th>
                          <th> 총 주문 금액 </th>
                          <th> 빈칸 예비용 </th>
                          <!-- <th> Start Date </th>
                          <th> Payment Status </th> -->
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                          <td>
                            <div class="form-check form-check-muted m-0">
                              <label class="form-check-label">
                                <input type="checkbox" class="form-check-input">
                              </label>
                            </div>
                          </td>
                          <td>
                            <img src="assets/images/faces/face1.jpg" alt="image" />
                            <span class="pl-2">이창훈</span>
                          </td>
                          <td> saihdfsiah@asiof.com </td>
                          <td> vip </td>
                          <td> 100000원 </td>
                          <td> 수정사항 넣기 </td>
                        </tr>
                        <tr>
                          <td>
                            <div class="form-check form-check-muted m-0">
                              <label class="form-check-label">
                                <input type="checkbox" class="form-check-input">
                              </label>
                            </div>
                          </td>
                          <td>
                            <img src="assets/images/faces/face2.jpg" alt="image" />
                            <span class="pl-2">Estella Bryan</span>
                          </td>
                          <td> 02312 </td>
                          <td> $14,500 </td>
                          <td> Website </td>
                          <td> </td>
                        </tr>
                        <tr>
                          <td>
                            <div class="form-check form-check-muted m-0">
                              <label class="form-check-label">
                                <input type="checkbox" class="form-check-input">
                              </label>
                            </div>
                          </td>
                          <td>
                            <img src="assets/images/faces/face5.jpg" alt="image" />
                            <span class="pl-2">Lucy Abbott</span>
                          </td>
                          <td> 02312 </td>
                          <td> $14,500 </td>
                          <td> App design </td>
                          <td> </td>
                        </tr>
                        <tr>
                          <td>
                            <div class="form-check form-check-muted m-0">
                              <label class="form-check-label">
                                <input type="checkbox" class="form-check-input">
                              </label>
                            </div>
                          </td>
                          <td>
                            <img src="assets/images/faces/face3.jpg" alt="image" />
                            <span class="pl-2">Peter Gill</span>
                          </td>
                          <td> 02312 </td>
                          <td> $14,500 </td>
                          <td> Development </td>
                          <td> </td>
                        </tr>
                        <tr>
                          <td>
                            <div class="form-check form-check-muted m-0">
                              <label class="form-check-label">
                                <input type="checkbox" class="form-check-input">
                              </label>
                            </div>
                          </td>
                          <td>
                            <img src="assets/images/faces/face4.jpg" alt="image" />
                            <span class="pl-2">Sallie Reyes</span>
                          </td>
                          <td> 02312 </td>
                          <td> $14,500 </td>
                          <td> Website </td>
                          <td> </td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">

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
export default {

}
</script>

<style>

</style>
