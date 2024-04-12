<template>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper">
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row ">
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h3 class="card-title">1:1 문의 상세조회</h3>
                  <br>
                  <div v-if="qna" class="article-container">
                    <div class="article-header">
                      <h4 class="">제목</h4>
                      <h5> {{ qna.title }}</h5>
                      <br>
                    </div>
                    <div class="article-content">
                      <h4>내용</h4>
                      <h5>{{ qna.qnaContent }}</h5>
                      <br>
                      <h4 v-if="qna.answerContent">답변</h4>
                      <h4 v-if="qna.answerContent">{{ qna.answerContent }}</h4>
                      <div v-else>
                        <textarea v-model="answerContent" class="form-control" placeholder="답변을 입력하세요"></textarea>
                        <hr>
                        <button class="btn btn-primary mr-2" @click="submitAnswer">등록하기</button>
                      </div>
                    </div>
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
let backend = "http://192.168.0.33:80/api";
// let backend = "http://localhost:8000";

export default {
  data() {
    return {
      qna: null,
      answerContent: ''
    };
  },
  mounted() {
    this.fetchQnaData();
  },
  methods: {
    fetchQnaData() {
      axios.post(backend + `/admin/qna/read/${this.$route.params.idx}`)
          .then(response => {
              if (response.data.code === 1000) {
              this.qna = response.data.result;
            } else {
              alert("오류");
            }
          })
          .catch(error => {
            console.error("에러 발생:", error);
          });
    },
    submitAnswer() {
      const requestData = {
        answerContent: this.answerContent
      };

      axios.post(backend + `/admin/qna/answer/${this.$route.params.idx}`, requestData, {
        headers:{
          Authorization: localStorage.getItem("accessToken")
        }
      }).then(response => {
            if (response.data.code === 1000) {
              alert("답변이 작성되었습니다.");
              this.$router.push({path: "/qnalist"});
            } else {
              alert("답변이 작성되지 않았습니다.");
            }
          })
          .catch(error => {
            console.error("에러 발생:", error);
          });
    }
  }
};
</script>

<style>
</style>
