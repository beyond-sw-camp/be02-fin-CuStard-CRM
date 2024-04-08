<template>
    <div class="container">
        <h2>판매자에게 문의하기</h2>
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
            <input type="hidden" name="product_name" value="상품명">
            <input type="text" v-model="subject" placeholder="제목" required>
            <textarea v-model="message" rows="6" placeholder="내용" required></textarea>
            <input type="password" v-model="password" placeholder="비밀번호" required class="left-align">
            <div class="text-center">
                <input type="submit" value="문의하기" class="submit-btn">
            </div>
        </form>
    </div>
</template>

<script>
import axios from 'axios';

//let backend = "http://192.168.0.31:80/api";
let backend = "http://localhost:8080";

export default {
    data() {
        return {
            selectedType: '', // 선택된 문의 종류를 저장하는 변수
            subject: '',
            message: '',
            password: ''
        };
    },
    methods: {
        submitForm() {
            axios.post(backend + '/qna/register', {
                title: this.subject,
                qnaContent: this.message,
                qnaPwd: this.password,
                category: this.selectedType // 선택된 체크박스의 값을 전송
            })
            .then((response) => {
                console.log("데이터 전송 성공:", response.data);
                this.subject = '';
                this.message = '';
                this.password = '';
                this.selectedType='';
                alert("문의를 성공적으로 작성했습니다");
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
    margin-top:50px;
    margin-bottom:50px;
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

input[type="text"], textarea {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

input[type="password"] {
    margin-right: 425px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
    width: 600px;
}

input[type="submit"] {
    background-color: #99154e;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 15px;
    transition: background-color 0.3s, color 0.3s;
}

input[type="submit"]:hover {
    background-color: #ffbe0e;
}

.checkbox-container {
    margin-bottom: 10px;
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
</style>
