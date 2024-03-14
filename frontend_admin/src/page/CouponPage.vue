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
