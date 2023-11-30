package com.example.todo.user;

import com.example.todo.user.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@ActiveProfiles("test")
//@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // 이상하게 Controller에서는 실제 데이터베이스에 작성된다.
    // Service Layer에서는 테스트 후 데이터베이스에 남지않는다.
//    @Test
//    @Transactional
    void getUser() throws Exception {
        User mockUser = new User("namu1234@gmail.com", "test123", "namu");
        userRepository.save(mockUser);

        Long userId = mockUser.getUserId();
        UserResponseDto mockUserResponseDto = mockUser.toResponseDto();

        given(userService.getUser(userId)).willReturn(mockUserResponseDto);

        mockMvc.perform(get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId.intValue()))
                .andExpect(jsonPath("$.loginId").value("namu1234@gmail.com"))
                .andExpect(jsonPath("$.password").value("test123"))
                .andExpect(jsonPath("$.name").value("namu"));
    }

}