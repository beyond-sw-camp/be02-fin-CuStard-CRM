<template>
    <div class="container-scroller">
    <!-- partial:partials/_sidebar.html -->
    <nav class="sidebar sidebar-offcanvas" id="sidebar">
      <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
        <a class="sidebar-brand brand-logo" href="index.html"><img src="assets/images/custard-dashboard-logo.png"
            width=30px alt="logo" /></a>
        <a class="sidebar-brand brand-logo-mini" href="index.html"><img src="assets/images/logo-mini.svg"
            alt="logo" /></a>
      </div>
      <ul class="nav">
        <li class="nav-item profile">
          <div class="profile-desc">
            <div class="profile-pic">
              <div class="count-indicator">
                <img class="img-xs rounded-circle " src="assets/images/faces/face15.jpg" alt="">
                <span class="count bg-success"></span>
              </div>
              <div class="profile-name">
                <h5 class="mb-0 font-weight-normal">Henry Klein</h5>
              </div>
            </div>
          </div>
        </li>
        <li class="nav-item menu-items">
          <a class="nav-link" href="index.html">
            <span class="menu-icon">
              <i class="mdi mdi-speedometer"></i>
            </span>
            <span class="menu-title">Dashboard</span>
          </a>
        </li>
        <li class="nav-item menu-items">
          <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
            <span class="menu-icon">
              <i class="mdi mdi-laptop"></i>
            </span>
            <span class="menu-title">1:1 문의</span>
            <i class="menu-arrow"></i>
          </a>
          <div class="collapse" id="ui-basic">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item"> <a class="nav-link" href="pages/ui-features/buttons.html">목록 조회</a></li>
              <li class="nav-item"> <a class="nav-link" href="pages/ui-features/dropdowns.html">미답변</a></li>
            </ul>
          </div>
        </li>
        <li class="nav-item menu-items">
          <a class="nav-link" href="pages/forms/basic_elements.html">
            <span class="menu-icon">
              <i class="mdi mdi-playlist-play"></i>
            </span>
            <span class="menu-title">고객</span>
          </a>
        </li>
        <li class="nav-item menu-items">
          <a class="nav-link" href="pages/tables/basic-table.html">
            <span class="menu-icon">
              <i class="mdi mdi-table-large"></i>
            </span>
            <span class="menu-title">쿠폰</span>
          </a>
        </li>
      </ul>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_navbar.html -->
      <nav class="navbar p-0 fixed-top d-flex flex-row">
        <div class="navbar-brand-wrapper d-flex d-lg-none align-items-center justify-content-center">
          <a class="navbar-brand brand-logo-mini" href="index.html"><img src="assets/images/logo-mini.svg"
              alt="logo" /></a>
        </div>
        <div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">
          <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
            <span class="mdi mdi-menu"></span>
          </button>
          <ul class="navbar-nav navbar-nav-right">
            <li class="nav-item dropdown">
              <a class="nav-link" id="profileDropdown" href="#" data-toggle="dropdown">
                <div class="navbar-profile">
                  <img class="img-xs rounded-circle" src="assets/images/faces/face15.jpg" alt="">
                  <p class="mb-0 d-none d-sm-block navbar-profile-name">Henry Klein</p>
                  <i class="mdi mdi-menu-down d-none d-sm-block"></i>
                </div>
              </a>
              <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list"
                aria-labelledby="profileDropdown">
                <h6 class="p-3 mb-0">Profile</h6>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item preview-item">
                  <div class="preview-thumbnail">
                    <div class="preview-icon bg-dark rounded-circle">
                      <i class="mdi mdi-logout text-danger"></i>
                    </div>
                  </div>
                  <div class="preview-item-content">
                    <p class="preview-subject mb-1">Log out</p>
                  </div>
                </a>
              </div>
            </li>
          </ul>
          <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button"
            data-toggle="offcanvas">
            <span class="mdi mdi-format-line-spacing"></span>
          </button>
        </div>
      </nav>
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