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
                          <th> 이름 </th>
                          <th> 아이디 </th>
                          <th> 나이 </th>
                          <th> 지역 </th>
                          <th> 등급 </th>
                          <th> 총 구매 금액</th>
                          <th> 마지막 접속일 </th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr class="table-info">
                          <td> 이창훈 </td>
                          <td> LCH-97 </td>
                          <td> 27 </td>
                          <td> 서울시 </td>
                          <td> VIP </td>
                          <td> 1000000 </td>
                          <td> 24-03-14-12-06-00 </td>
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
                    <table class="table">
                      <thead>
                        <tr>
                          <th>번호</th>
                          <th>문의제목</th>
                          <th>문의 날짜</th>
                          <th>상태</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>1</td>
                          <td>저기요</td>
                          <td>12 May 2024</td>
                          <td>답변완료</td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>즈기요</td>
                          <td>15 May 2024</td>
                          <td>미답변</td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>hi</td>
                          <td>15 May 2024</td>
                          <td>답변 대기</td>
                        </tr>
                        <tr>
                          <td>4</td>
                          <td>hey</td>
                          <td>15 May 2024</td>
                          <td>답변완료</td>
                        </tr>
                        <tr>
                          <td>5</td>
                          <td>즈기요</td>
                          <td>15 May 2024</td>
                          <td>미답변</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
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
                <div class="card-body"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
                  <h4 class="card-title">카테고리 별 구매 금액 </h4>
                  <canvas id="barChart" style="height: 175px; display: block; width: 351px;" width="438" height="218" class="chartjs-render-monitor"></canvas>
                </div>
              </div>
            </div>
            <div class="col-lg-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
                  <h4 class="card-title">조회 카테고리  </h4>
                  <canvas id="pieChart" style="height: 185px; display: block; width: 371px;" width="463" height="231" class="chartjs-render-monitor"></canvas>
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
    export default {

    }
</script>

<style>

</style>
