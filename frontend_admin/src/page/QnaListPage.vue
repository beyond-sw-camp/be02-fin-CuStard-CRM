<template>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper">
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row ">
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h3 class="card-title">1:1 문의</h3>
                  <div class="table-responsive">
                    <h4>답변 대기</h4>
                    <table class="table">
                      <thead>
                      <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>답변 상태</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr v-for="qna in qnasWaiting" :key="qna.idx" @click="goToArticle(qna.idx)">
                        <td>{{ qna.idx }}</td>
                        <td>
                          <span class="title-text">{{ qna.title }}</span>
                        </td>
                        <td>
                          <div class="badge badge-outline-danger">
                            답변 대기
                          </div>
                        </td>
                      </tr>
                      </tbody>
                    </table>
                    <h4>답변 완료</h4>
                    <table class="table">
                      <thead>
                      <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>답변 상태</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr v-for="qna in qnasAnswered" :key="qna.idx" @click="goToArticle(qna.idx)">
                        <td>{{ qna.idx }}</td>
                        <td>
                          <span class="title-text">{{ qna.title }}</span>
                        </td>
                        <td>
                          <div class="badge badge-outline-success">
                            답변 완료
                          </div>
                        </td>
                      </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
let backend = "http://192.168.0.52:80/api";

export default {
  data() {
    return {
      qnasWaiting: [], // 답변 대기 목록
      qnasAnswered: [] // 답변 완료 목록
    };
  },
  methods: {
    loadArticles() {
      axios.get(backend + "/admin/qna/list")
          .then((response) => {
            this.qnasWaiting = response.data.result.filter(qna => !qna.answerContent);
            this.qnasAnswered = response.data.result.filter(qna => qna.answerContent);
          })
          .catch((error) => {
            console.error("데이터 로드 실패:", error);
          });
    },
    goToArticle(idx) {
      this.$router.push({path: `/qnaread${idx}`});
    }
  },
  mounted() {
    this.loadArticles();
  }
};
</script>

<style>

</style>

