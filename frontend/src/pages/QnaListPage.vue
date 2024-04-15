np<template>
  <div class="qna-list">
    <ul>
      <li v-for="qna in qnas" :key="qna.idx" @click="goToArticle(qna.idx)">
        <div :class="['qna-item', qna.answerContent !== null ? 'qna-with-answer' : 'qna-without-answer']">
          <span>{{ qna.idx }}:</span>
          <span>{{ qna.title }}</span>
          <p>{{ qna.answerContent !== null ? '답변 완료' : '답변 대기' }}</p>
        </div>
      </li>
    </ul>
    <button @click="goToRegister" class="write-button">문의 글 작성</button>
  </div>
</template>

<script>
import axios from 'axios';
let backend = "http://192.168.0.31:80/api";
// let backend = "http://localhost:8080";

export default {
  data() {
    return {
      qnas: []
    };
  },
  methods: {
    loadArticles() {
      axios.get(backend + '/qna/list')
          .then((response) => {
            this.qnas = response.data.result;
          })
          .catch((error) => {
            console.error("데이터 로드 실패:", error);
          });
    },
    goToArticle(idx) {
      this.$router.push({ path: `/qna/read/${idx}` });
    },
    goToRegister() {
      this.$router.push({ path: "/qna/write" });
    }
  },
  mounted() {
    this.loadArticles();
  }
};
</script>

<style scoped>
.qna-list {
  max-width: 800px;
  margin: 0 auto;
  margin-top: 60px;
  padding-top: 20px; /* 위쪽 여백 */
  padding-bottom: 20%; /* 아래쪽 여백 */
}

ul {
  padding: 0;
  list-style: none;
}

.qna-item {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
  cursor: pointer;
}

.qna-item span {
  font-weight: bold;
}

.qna-item p {
  margin-top: 5px;
}

.qna-with-answer {
  background-color: #e6ffe6;
}

.qna-without-answer {
  background-color: #fff;
}

.write-button {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #99154E;
  transition: background-color 0.5s;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;

}

.write-button:hover {
  background-color: #ffbe0e;
}
</style>