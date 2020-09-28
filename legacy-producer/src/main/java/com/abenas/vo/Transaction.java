package com.abenas.vo;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;

public class Transaction {

    @CsvBindByName
    public int fiscalYear;

    @CsvBindByName
    public int fiscalPeriod;

    @CsvBindByName
    public String department;

    @CsvBindByName
    public String division;

    @CsvBindByName
    public String merchant;

    @CsvBindByName
    public String category;

    @CsvBindByName
    public String creationDate;

    @CsvBindByName
    public BigDecimal amount;

    public int getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(int fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public int getFiscalPeriod() {
        return fiscalPeriod;
    }

    public void setFiscalPeriod(int fiscalPeriod) {
        this.fiscalPeriod = fiscalPeriod;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "fiscalYear=" + fiscalYear +
                ", fiscalPeriod=" + fiscalPeriod +
                ", department='" + department + '\'' +
                ", division='" + division + '\'' +
                ", merchant='" + merchant + '\'' +
                ", category='" + category + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", amount=" + amount +
                '}';
    }
}
