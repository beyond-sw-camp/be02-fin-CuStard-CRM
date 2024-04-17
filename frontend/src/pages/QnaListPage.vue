<template>
  <div class="inquiry-container">
    <h2>1:1 문의하기</h2>
    <div class="table-container">
      <table>
        <thead>
        <tr>
          <th>NO</th>
          <th>문의 유형</th>
          <th>문의 제목</th>
          <th>문의 내용</th>
          <th>상태</th>
        </tr>
        </thead>
        <tbody v-for="qna in qnas" :key="qna.idx" @click="goToArticle(qna.idx)">
        <tr :class="['qna-item', qna.answerContent !== null ? 'qna-with-answer' : 'qna-without-answer']">
          <td>{{ qna.idx }}</td>
          <td>{{qna.category}}</td>
          <td>{{ qna.title }}</td>
          <td>{{qna.qnaContent}}</td>
          <td>{{ qna.answerContent !== null ? '답변 완료' : '답변 대기' }}</td>
        </tr>
        </tbody>
      </table>
    </div>
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
      qnas: [],
    };
  },
  methods: {
    loadArticles() {
      axios.get(backend + '/qna/list')
          .then((response) => {
            console.log(response.data.result);
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
  background-color: #fff;
}

.qna-without-answer {
  background-color: #e7eae7ba;
}

.write-button {
  transition: background-color 0.5s;
  color: #000;
  border: none;
  width: 105px;
  height: 36px;
  border-radius: 13px;
  background: rgb(255 255 255 / 0%);
  cursor: pointer;
  position: absolute;
  top: 167px;
  font-size: 13px;
  right: 71px;
  border: 1px solid black;
}

.write-button:hover {
  background-color: black;
  color: #fff;
}

/*추가 css*/
.inquiry-container {
  width: 100%;
  max-width: 1440px; /* 이미지에 맞게 조정해야 할 수도 있습니다. */
  margin: 0 auto;
  padding: 20px;
  box-sizing: border-box;
  text-align: center;
}

h2 {
  font-size: 24px;
  margin-bottom: 20px;
}

.table-container {
  background: #fff;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 효과 추가 */
  position: relative;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

table th, table td {
  border-bottom: 1px solid #ddd;
  padding: 15px;
  text-align: left;
}

table th {
  background: #f8f8f8;
}

table tbody tr:last-child td {
  border-bottom: none;
}



</style>
