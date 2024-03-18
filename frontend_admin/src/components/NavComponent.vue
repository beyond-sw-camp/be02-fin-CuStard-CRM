<template>
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
                Admin
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
              <a @click="logout()">
                <div class="preview-item-content">
                  <p class="preview-subject mb-1">Log out</p>
                </div>
              </a>
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



</template>

<script>
import { useRouter } from 'vue-router';

const router = useRouter();

export default {
  methods: {
    logout() {
      localStorage.removeItem("accessToken");
      localStorage.removeItem("adminIdx");

      router.push("/");
      router.go();
    }
  }
}
</script>

<style>

</style>
