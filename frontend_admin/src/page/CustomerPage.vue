<template>
  <div class="container-scroller">
    <!-- partial:partials/_sidebar.html -->
    <nav class="sidebar sidebar-offcanvas" id="sidebar">
      <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
        <a class="sidebar-brand brand-logo" href="index.html"><img src="assets/images/custard-dashboard-logo.png" width=30px alt="logo" /></a>
        <a class="sidebar-brand brand-logo-mini" href="index.html"><img src="assets/images/logo-mini.svg" alt="logo" /></a>
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
          <a class="navbar-brand brand-logo-mini" href="index.html"><img src="assets/images/logo-mini.svg" alt="logo" /></a>
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
              <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="profileDropdown">
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
          <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
            <span class="mdi mdi-format-line-spacing"></span>
          </button>
        </div>
      </nav>
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
