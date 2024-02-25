package com.ohgiraffers.model.dto;

public class CreateEmployeeDTO {
    private String empId;               // 직원 ID
    private String empPwd;              // 직원 PW
    private String empName;             // 직원 이름
    private String phone;               // 연락처(String인 이유 : 기입을 '-' 포함하기 위해)
    private String email;               // 이메일
    private String departmentCode;      // 부서코드(DP1 ~ DP5) 경영지원팀, 영업팀, 개발팀, 마케팅팀, 고객서비스팀
    private String positionCode;        // 직책코드(PS1 ~ PS5) 사장, 부장, 과장, 대리, 사원
    private String empIdentification;     // 권한(admin / mgr / emp) 관리자, 중간관리자(부장), 일반직원
    private String paymentCode;         // 급여코드(PM1 ~ PM5) PM1 : 800 / PM2 : 500 / PM3 : 400 / PM4 : 300 / PM5 : 200

    /* 기본생성자 생성 */
    public CreateEmployeeDTO() {

    }

    /* 매개변수가 있는 생성자 생성 */

    public CreateEmployeeDTO(String empId, String empPwd, String empName, String phone, String email, String departmentCode, String positionCode, String empIdentification, String paymentCode) {
        this.empId = empId;
        this.empPwd = empPwd;
        this.empName = empName;
        this.phone = phone;
        this.email = email;
        this.departmentCode = departmentCode;
        this.positionCode = positionCode;
        this.empIdentification = empIdentification;
        this.paymentCode = paymentCode;
    }
    /* getter / setter 생성 */

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpPwd() {
        return empPwd;
    }

    public void setEmpPwd(String empPwd) {
        this.empPwd = empPwd;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getEmpIdentification() {
        return empIdentification;
    }

    public void setEmpIdentification(String empIdentification) {
        this.empIdentification = empIdentification;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }
    /* toString 생성 */

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empId='" + empId + '\'' +
                ", empPwd='" + empPwd + '\'' +
                ", empName='" + empName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                ", positionCode='" + positionCode + '\'' +
                ", empIdentification='" + empIdentification + '\'' +
                ", paymentCode='" + paymentCode + '\'' +
                '}';
    }
}
