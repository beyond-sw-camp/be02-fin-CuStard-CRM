<template>
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
              <h5 class="mb-0 font-weight-normal">Admin</h5>
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
        <a class="nav-link" href="/">
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
              <a class="nav-link" href="/qnaread:idx"
              >목록 조회</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/qnalist"
              >미답변</a
              >
            </li>
          </ul>
        </div>
      </li>
      <li class="nav-item menu-items">
        <a class="nav-link" href="/customerlist">
              <span class="menu-icon">
                <i class="mdi mdi-playlist-play"></i>
              </span>
          <span class="menu-title">고객</span>
        </a>
      </li>
      <li class="nav-item menu-items">
        <a class="nav-link" href="/coupon">
              <span class="menu-icon">
                <i class="mdi mdi-table-large"></i>
              </span>
          <span class="menu-title">쿠폰</span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {

}
</script>

<style>

</style>
