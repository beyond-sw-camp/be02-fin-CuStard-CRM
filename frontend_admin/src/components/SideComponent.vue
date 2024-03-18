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
        <a class="nav-link" href="/qnalist">
              <span class="menu-icon">
                <i class="mdi mdi-speedometer"></i>
              </span>
          <span class="menu-title">1:1 문의</span>
        </a>
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
