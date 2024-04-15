<template>
    <div class="container">
        <h2>판매자에게 문의하기</h2>
        <main>
            <form @submit.prevent="submitForm">
                <div class="checkbox-container">
                    <label for="product">
                        <input type="radio" id="product" value="상품" v-model="selectedType">
                        상품
                    </label>
                    <label for="delivery">
                        <input type="radio" id="delivery" value="배송" v-model="selectedType">
                        배송
                    </label>
                    <label for="cancel">
                        <input type="radio" id="cancel" value="취소" v-model="selectedType">
                        취소
                    </label>
                    <label for="return">
                        <input type="radio" id="return" value="반품/환불" v-model="selectedType">
                        반품/환불
                    </label>
                    <label for="exchange">
                        <input type="radio" id="exchange" value="교환" v-model="selectedType">
                        교환
                    </label>
                    <label for="etc">
                        <input type="radio" id="etc" value="기타" v-model="selectedType">
                        기타
                    </label>
                </div>

                <label for="title" class="title-register">제목</label>
                <input type="text" id="title" v-model="title" required placeholder="제목을 입력하세요">
<br>
                <label for="content" class="content-register">내용</label>
                <textarea id="content" v-model="content" rows="4" required placeholder="내용을 입력하세요"></textarea>
<br>
                <label for="password" class="password-register">비밀번호</label>
                <input type="password" id="password" v-model="password" required placeholder="비밀번호를 입력하세요">

                <button type="submit" class="submit-button">문의하기</button>
            </form>
        </main>
    </div>
</template>

<script>
import axios from 'axios';

let backend = "http://192.168.0.31:80/api";
// let backend = "http://localhost:8080";
export default {
    data() {
        return {
            title: '',
            content: '',
            password: '',
            articles: [],
            selectedType: '' // 선택된 문의 종류를 저장하는 변수
        };
    },
    methods: {
        submitForm() {
            const accessToken = localStorage.getItem('accessToken');
            console.log(accessToken)
            axios.post(backend + '/qna/register', {
                title: this.title,
                qnaContent: this.content,
                qnaPwd: this.password,
                category: this.selectedType
            } /*선택된 체크박스의 값을 전송}*/, {
                headers: { 'Authorization': `${accessToken}` },
            })
                .then((response) => {
                    console.log("데이터 전송 성공:", response.data);
                    this.title = '';
                    this.content = '';
                    this.password = '';
                    this.selectedType = '';
                    alert("문의를 성공적으로 작성했습니다");
                    this.$router.push("/qna/list");
                    // 페이지 리디렉션 등의 추가 작업이 필요하다면 여기에서 처리할 수 있습니다.
                })
                .catch((error) => {
                    console.error("데이터 전송 실패:", error);
                });
        }
    }
};
</script>

<style scoped>
.container {
    max-width: 600px;
    margin: 20px auto;
    margin-top: 50px;
    margin-bottom: 50px;
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    text-align: center;
    border: 1px solid #ccc;
    border-top: 2px solid #333;
}

h2 {
    margin-top: 0;
}

input[type="text"],
textarea,
input[type="password"] {
    width: calc(100% - 22px); /* Adjusted width to accommodate padding and border */
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

.submit-button {
    background-color: #99154e;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 15px;
    transition: background-color 0.3s, color 0.3s;
}

.submit-button:hover {
    background-color: #ffbe0e;
}

.checkbox-container {
    margin-bottom: 30px;
    margin-left: 10px;
    display: flex;
    flex-wrap: wrap;
}

.checkbox-container label {
    margin-right: 15px;
}

.checkbox-container label input[type="checkbox"] {
    margin-right: 5px;
}

.text-center {
    text-align: center;
}
.title-register {
    margin-right: 540px;
}

.content-register {
    margin-right: 540px;
}

.password-register {
    margin-right: 510px;
}
</style>