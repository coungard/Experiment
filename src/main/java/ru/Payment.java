package ru;

public class Payment {

    private long id;
    private String number;
    private String text;
    private String sum;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", text='" + text + '\'' +
                ", sum=" + sum +
                '}';
    }
}
