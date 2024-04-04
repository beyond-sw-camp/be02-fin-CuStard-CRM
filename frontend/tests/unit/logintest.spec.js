import { shallowMount } from '@vue/test-utils'
import LoginPage from '@/pages/LoginPage.vue'
import { useCustomerStore } from '@/stores/useCustomerStore'

jest.mock('@/stores/useCustomerStore')

describe('LoginPage.vue', () => {

  beforeEach(() => {
    useCustomerStore.mockClear()
  })

  it('로그인 폼 입력 렌더링에 대한 테스트', () => {
    const wrapper = shallowMount(LoginPage)
    
    // 이메일 입력란이 존재하는지 확인
    const emailInput = wrapper.find('input[type="text"][id="email"]')
    expect(emailInput.exists()).toBe(true)

    // 비밀번호 입력란이 존재하는지 확인
    const passwordInput = wrapper.find('input[type="password"][id="password"]')
    expect(passwordInput.exists()).toBe(true)

    // 로그인 버튼이 존재하는지 확인
    const loginButton = wrapper.find('button[type="submit"]')
    expect(loginButton.exists()).toBe(true)
  })

})
