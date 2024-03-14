<template>
  <div class="container-scroller">
    <!-- partial:partials/_sidebar.html -->
    <nav class="sidebar sidebar-offcanvas" id="sidebar">
      <div
          class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top"
      >
        <a class="sidebar-brand brand-logo" href="/"
        ><img
            src="assets/images/custard-dashboard-logo.png"
            width="30px"
            alt="logo"
        /></a>
        <a class="sidebar-brand brand-logo-mini" href="/"
        ><img src="assets/images/logo-mini.svg" alt="logo"
        /></a>
      </div>
      <ul class="nav">
        <li class="nav-item profile">
          <div class="profile-desc">
            <div class="profile-pic">
              <div class="count-indicator">
                <img
                    class="img-xs rounded-circle"
                    src="assets/images/faces/face15.jpg"
                    alt=""
                />
                <span class="count bg-success"></span>
              </div>
              <div class="profile-name">
                <h5 class="mb-0 font-weight-normal">Henry Klein</h5>
              </div>
            </div>
            <!-- <a href="#" id="profile-dropdown" data-toggle="dropdown"><i class="mdi mdi-dots-vertical"></i></a>
            <div class="dropdown-menu dropdown-menu-right sidebar-dropdown preview-list" aria-labelledby="profile-dropdown">
              <a href="#" class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-dark rounded-circle">
                    <i class="mdi mdi-settings text-primary"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <p class="preview-subject ellipsis mb-1 text-small">Account settings</p>
                </div>
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-dark rounded-circle">
                    <i class="mdi mdi-onepassword  text-info"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <p class="preview-subject ellipsis mb-1 text-small">Change Password</p>
                </div>
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-dark rounded-circle">
                    <i class="mdi mdi-calendar-today text-success"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <p class="preview-subject ellipsis mb-1 text-small">To-do list</p>
                </div>
              </a>
            </div> -->
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
          <a
              class="nav-link"
              data-toggle="collapse"
              href="#ui-basic"
              aria-expanded="false"
              aria-controls="ui-basic"
          >
              <span class="menu-icon">
                <i class="mdi mdi-laptop"></i>
              </span>
            <span class="menu-title">1:1 문의</span>
            <i class="menu-arrow"></i>
          </a>
          <div class="collapse" id="ui-basic">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <a class="nav-link" href="pages/ui-features/buttons.html"
                >목록 조회</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" href="pages/ui-features/dropdowns.html"
                >미답변</a
                >
              </li>
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
        <div
            class="navbar-brand-wrapper d-flex d-lg-none align-items-center justify-content-center"
        >
          <a class="navbar-brand brand-logo-mini" href="index.html"
          ><img src="assets/images/logo-mini.svg" alt="logo"
          /></a>
        </div>
        <div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">
          <button
              class="navbar-toggler navbar-toggler align-self-center"
              type="button"
              data-toggle="minimize"
          >
            <span class="mdi mdi-menu"></span>
          </button>
          <ul class="navbar-nav navbar-nav-right">
            <li class="nav-item dropdown">
              <a
                  class="nav-link"
                  id="profileDropdown"
                  href="#"
                  data-toggle="dropdown"
              >
                <div class="navbar-profile">
                  <img
                      class="img-xs rounded-circle"
                      src="assets/images/faces/face15.jpg"
                      alt=""
                  />
                  <p class="mb-0 d-none d-sm-block navbar-profile-name">
                    Henry Klein
                  </p>
                  <i class="mdi mdi-menu-down d-none d-sm-block"></i>
                </div>
              </a>
              <div
                  class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list"
                  aria-labelledby="profileDropdown"
              >
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
          <button
              class="navbar-toggler navbar-toggler-right d-lg-none align-self-center"
              type="button"
              data-toggle="offcanvas"
          >
            <span class="mdi mdi-format-line-spacing"></span>
          </button>
        </div>
      </nav>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <h6 class="text-muted font-weight-normal" style="padding-left:20px; padding-bottom: 5px;"> 오늘 03:00시 기준</h6>
          <div class="row">
            <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">방문자 수</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:250px;">
                        <div style="font-size: 25px; font-weight: 500;">1833명</div>
                        <div class="text-success ml-2 mb-0 font-weight-medium" style="font-size: 15px; font-weight: 500; padding-left: 5px;">
                          +21명
                        </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div class="icon icon-box-success">
                          <span
                              class="mdi mdi-arrow-top-right icon-item"
                          ></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">결제건 수</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:150px;">
                        <div style="font-size: 25px; font-weight: 500;">67건</div>
                        <div class="text-success ml-2 mb-0 font-weight-medium" style="font-size: 15px; font-weight: 500; padding-left: 5px;">
                          +21건
                        </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div class="icon icon-box-success">
                          <span
                              class="mdi mdi-arrow-top-right icon-item"
                          ></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">신규 유입</h4>
                      <div class="d-flex align-items-center align-self-start" style="width:150px;">
                        <div style="font-size: 25px; font-weight: 500;">12명</div>
                        <div class="text-danger ml-2 mb-0 font-weight-medium" style="font-size: 15px; font-weight: 500; padding-left: 5px;">
                          -3명
                        </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div class="icon icon-box-danger">
                          <span
                              class="mdi mdi-arrow-bottom-left icon-item"
                          ></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">휴면고객 재접속률</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:250px;">
                        <div style="font-size: 25px; font-weight: 500;">19명</div>
                        <div class="text-success ml-2 mb-0 font-weight-medium" style="font-size: 15px; font-weight: 500; padding-left: 5px;">
                          +5명
                        </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div class="icon icon-box-success">
                          <span
                              class="mdi mdi-arrow-top-right icon-item"
                          ></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-9">
                      <h4 class="text-muted font-weight-normal">하루 매출액</h4>
                      <div class="d-flex align-items-center align-self-start" style="  width:250px;">
                        <div style="font-size: 25px; font-weight: 500;">384778원</div>
                        <div class="text-success ml-2 mb-0 font-weight-medium" style="font-size: 15px; font-weight: 500; padding-left: 5px;">
                          +2400원
                        </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div class="icon icon-box-success">
                          <span
                              class="mdi mdi-arrow-top-right icon-item"
                          ></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-6   grid-margin stretch-card">
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
                  <h4 class="card-title" style="width:280px;">카테고리 별 판매율</h4>
                  <canvas
                      id="doughnutChart"
                      style="height: 130px; display: block; width: 300px"
                      width="317"
                      height="130"
                      class="chartjs-render-monitor"
                  ></canvas>
                  <div
                      class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3" style="height:40px" >
                    <div> 지난 14일</div>
                    <div class="text-md-center text-xl-left">
                      <h6 class="mb-1">결제 수</h6>
                    </div>
                    <div
                        class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                      <h6 class="font-weight-bold mb-0">842건</h6>
                    </div>
                  </div>
                  <div
                      class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3" style="height:40px">
                    <div class="text-md-center text-xl-left">
                      <h6 class="mb-1">총 결제 금액</h6>
                    </div>
                    <div
                        class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                      <h6 class="font-weight-bold mb-0">5923400원</h6>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title" style="padding-bottom: 10px;">1:1 문의내역 처리 현황 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 미답변 내역 3개 </h4>
                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead>
                      <tr>
                        <th>User</th>
                        <th>Title</th>
                        <th>Status</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr>
                        <td>Jacob</td>
                        <td>배송이 7일째 안옵니다..</td>
                        <td><label class="badge badge-danger">Pending</label></td>
                      </tr>
                      <tr>
                        <td>Messsy</td>
                        <td>상품이 고장나서 왔어요</td>
                        <td><label class="badge badge-danger">Pending</label></td>
                      </tr>
                      <tr>
                        <td>John</td>
                        <td>상한 음식이 온 것 같아요</td>
                        <td><label class="badge badge-danger">Pending</label></td>
                      </tr>
                      <tr>
                        <td>Peter</td>
                        <td>단순변심 환불도 가능할까요?</td>
                        <td><label class="badge badge-success">Completed</label></td>
                      </tr>
                      <tr>
                        <td>Dave</td>
                        <td>배송이 언제 올까요?</td>
                        <td><label class="badge badge-success">Completed</label></td>
                      </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-6 grid-margin stretch-card" >
              <div class="card">
                <div class="card-body"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
                  <h4 class="card-title">월별 매출 </h4>
                  <canvas id="barChart" style="height: 180px; display: block; width: 320px;" width="358" height="180" class="chartjs-render-monitor"></canvas>
                </div>
              </div>
            </div>
            <div class="col-lg-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
                  <h4 class="card-title">시간대 별 로그인</h4>
                  <canvas id="areaChart" style="height: 198px; display: block; width: 396px;" width="396" height="198" class="chartjs-render-monitor"></canvas>

                </div>
              </div>
            </div>
          </div>

        </div>
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <footer class="footer">
          <div
              class="d-sm-flex justify-content-center justify-content-sm-between"
          >
              <span
                  class="text-muted d-block text-center text-sm-left d-sm-inline-block"
              >Copyright © bootstrapdash.com 2020</span
              >
            <span
                class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center"
            >
                Free
                <a
                    href="https://www.bootstrapdash.com/bootstrap-admin-template/"
                    target="_blank"
                >Bootstrap admin templates</a
                >
                from Bootstrapdash.com</span
            >
          </div>
        </footer>
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
</template>

<script>

</script>

<style>

</style>