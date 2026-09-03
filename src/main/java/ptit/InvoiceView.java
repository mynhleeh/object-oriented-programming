package ptit;

import java.util.*;

class Subject {
    String SubjectId, SubjectName;
    int Credit;

    public Subject(String id, String name, int credit) {
        this.SubjectId = id;
        this.SubjectName = name;
        this.Credit = credit;
    }
}

class PaymentController {
    String StudentId, StudentName, SoQuyDinh, TenQuyDinh;
    int NumSubject;
    double Fee;
    List<Subject> Subjects = new ArrayList<>();

    public PaymentController() {
        Scanner scn = new Scanner(System.in);

        this.StudentId = scn.nextLine();
        this.StudentName = scn.nextLine();
        this.NumSubject = scn.nextInt();

        scn.nextLine();
        for (int i = 1; i <= this.NumSubject; i++) {
            String id = scn.nextLine();
            String sub = scn.nextLine();
            int credit = scn.nextInt();

            Subjects.add(new Subject(id, sub, credit));
            scn.nextLine();
        }

        this.SoQuyDinh = scn.nextLine();
        this.TenQuyDinh = scn.nextLine();
        this.Fee = scn.nextDouble();

        scn.close();
    }

    public Invoice getInvoice() {
        return new Invoice(
                this.StudentId,
                this.StudentName,
                this.NumSubject,
                this.Subjects,
                this.SoQuyDinh,
                this.TenQuyDinh,
                this.Fee);
    }
}

class Invoice {
    String StudentId, StudentName, SoQuyDinh, TenQuyDinh;
    int NumSubject;
    double Fee;
    List<Subject> Subjects;

    public Invoice(
            String StudentId,
            String StudentName,
            int NumSubject,
            List<Subject> Subjects,
            String SoQuyDinh,
            String TenQuyDinh,
            double Fee) {
        this.StudentId = StudentId;
        this.StudentName = StudentName;
        this.NumSubject = NumSubject;
        this.Subjects = Subjects;
        this.SoQuyDinh = SoQuyDinh;
        this.TenQuyDinh = TenQuyDinh;
        this.Fee = Fee;
    }
}

public class InvoiceView {
    public static void show(Invoice invoice) {
        System.out.println("Mã sinh viên: " + invoice.StudentId);
        System.out.println("Họ tên: " + invoice.StudentName);
        System.out.println("Các môn học:");

        double TotalFee = 0;
        for (Subject sub : invoice.Subjects) {
            TotalFee += invoice.Fee * sub.Credit;
            System.out.println(sub.SubjectName);
        }

        System.out.println("Học phí phải nộp là: " + TotalFee);
        System.out.println("Theo QĐ: " + invoice.SoQuyDinh);
    }

    public static void main(String[] args) {
        PaymentController pc = new PaymentController();
        // Output for test
        Invoice invoice = pc.getInvoice();
        InvoiceView.show(invoice);
    }
}