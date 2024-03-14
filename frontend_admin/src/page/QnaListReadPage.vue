<template>
  <div class="row ">
    <div class="col-12 grid-margin">
      <div class="card">
        <div class="card-body">
          <h3 class="card-title">1:1 문의 상세조회</h3>
          <div v-if="qna" class="article-container">
            <div class="article-header">
              <h4>{{ qna.title }}</h4>
            </div>
            <div class="article-content">
              <h4>{{ qna.qnaContent }}</h4>
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

</template>

<script>
import axios from 'axios';

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
      axios.post(`http://localhost:8000/admin/qna/read/${this.$route.params.idx}`)
        .then(response => {
          console.log(response.data); // 서버로부터 받은 데이터 확인
          if (response.status === 200) {
            this.qna = response.data;
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

      axios.post(`http://localhost:8000/admin/qna/answer/${this.$route.params.idx}`, requestData,{
        headers: {
    'Authorization': 'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Imp1aHllb24wOTI4QGdtYWlsLmNvbSIsInJvbGUiOiJBZG1pbmlzdHJhdG9yIiwiaWR4IjoxLCJpYXQiOjE3MTAyMjE4NzMsImV4cCI6MTcxMDUyMTg3M30.MLsKM9L3XZclTkm_WWaSxFkiffk-B_MoDL9Q4wNwm7I'
  }
      })
        .then(response => {
          if (response.status === 200) {
            alert("답변이 작성되었습니다.");
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
 @import url("../assets/css/style.css");
</style>