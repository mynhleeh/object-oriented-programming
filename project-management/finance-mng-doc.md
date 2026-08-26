# Tài liệu mô tả file `finance_7_7.xlsm`

## 1. Tổng quan

`finance_7_7.xlsm` là một **workbook Excel quản lý tài chính cá nhân** (personal finance tracker), có bật macro (VBA). File dùng để theo dõi: dòng tiền (thu/chi), số dư các ví/tài khoản, sổ tiết kiệm, chứng chỉ quỹ đầu tư, công nợ (cho vay/đi vay), và tổng hợp thành một "quỹ" (fund) theo mục tiêu tài chính (emergency, health, retirement...).

Về chủ sở hữu dữ liệu: file chứa dữ liệu tài chính cá nhân của một người dùng (có một tên người xuất hiện trong mục công nợ) — thông tin cá nhân này không được liệt kê chi tiết trong tài liệu này.

- **Định dạng:** `.xlsm` (Excel Macro-Enabled Workbook)
- **Số sheet:** 5 (`dashboard`, `transactions`, `debts`, `finance`, `database`)
- **VBA:** 4 module chuẩn (`.bas`) + 1 module `ThisWorkbook` + 2 sheet có code sự kiện (`Sheet2` = transactions)
- **Excel Table (ListObject):** 18 bảng
- **PivotTable:** 9 pivot, dựa trên 9 PivotCache
- **Chart:** 18 biểu đồ (13 trên `dashboard`, 5 trên `finance`)
- **Phạm vi dữ liệu giao dịch:** từ 04/04/2025 đến 20/08/2026 (~1.181 dòng giao dịch)

### Kiến trúc tổng thể

```
database  ──(danh mục phân loại: type/group/category, kỳ hạn, loại quỹ...)──┐
                                                                              │
transactions ──(wallets, transactions, transfers)──┐                        │
                                                     │                       │
debts ──(bảng debt: vay/mượn/thu/trả)───────────────┼──> finance (savings, fund_certs, funds, funds_cash)
                                                     │           │
                                                     └───────────┴──> dashboard (tổng hợp, pivot, chart)
```

`database` đóng vai trò **danh mục tham chiếu (lookup/master data)** dùng cho các danh sách xổ xuống (dropdown) và các công thức phân loại; `transactions`, `debts`, `finance` là **nơi nhập liệu**; `dashboard` là **lớp trình bày** (tổng hợp bằng công thức, PivotTable, Chart).

---

## 2. Chi tiết từng Sheet

### 2.1 Sheet `dashboard` (vùng dùng B2:X100)

Trang tổng quan, gần như toàn bộ là **công thức tham chiếu** tới 4 sheet còn lại, cùng bảng Excel `months_stat` và các PivotTable/Chart minh họa.

| Khu vực | Vị trí | Nội dung |
|---|---|---|
| Tiêu đề | B2 | `"D A S H B O A R D"` |
| **Assets theo ví** | J5:N13 | Số dư từng ví (`cash`, `mb bank`, `timo`, `vietcombank`, `msb`, `shopee pay`) và tỷ trọng (%), lấy qua công thức mảng liên kết tới bảng `wallets` bên sheet `transactions` |
| **Assets tổng hợp** | E17:G23 | Tổng hợp 5 cấu phần tài sản: `wallets`, `savings`, `investments` (fund_certs), `receivable` (phải thu), `payable` (phải trả) — mỗi dòng có số tiền (F) và % trên tổng (G) |
| **Funds** (theo mục tiêu) | J17, L19:N23 | Số dư từng "quỹ mục tiêu" (`emergency`, `health`, `target`, `retirement`) tra cứu bằng `VLOOKUP` vào bảng `funds` ở sheet `finance`, và tỷ trọng % |
| **Bảng `months_stat`** | P29:T42 | Thống kê 12 tháng gần nhất: `month`, `income`, `expense`, `cash flow`, `savings rate` — mỗi dòng dùng `SUMIFS` quét bảng `transactions[]` theo khoảng ngày trong tháng đó; dòng đầu tiên tính lùi từ tháng hiện tại bằng công thức `DATE(YEAR(...),MONTH(...)-1,1)` móc nối dòng dưới lên dòng trên |
| **PivotTable thu/chi tháng này** | R65:T93 | 2 pivot: thu nhập theo `category` (lọc `type=income`, `this month=TRUE`) và chi tiêu theo `category` (lọc `type=expense`, `this month=TRUE`) — nguồn dữ liệu là bảng `transactions` |
| **13 Chart** | rải rác trong vùng trên | Biểu đồ donut (cơ cấu tài sản/quỹ), line (biến động số dư ví theo thời gian, cash flow), bar (thu/chi theo nhóm & danh mục, tình trạng đạt mục tiêu quỹ) |

Bảng `months_stat` (P29:T42) là **Excel Table**, các công thức cột `month` móc nối lùi dần (dòng dưới tham chiếu dòng trên trừ 1 tháng), dòng cuối cùng (P42) neo vào `DATE(YEAR(TODAY()), MONTH(TODAY()), 1)` = tháng hiện tại. Nhờ vậy toàn bộ 12 tháng tự trượt theo ngày hệ thống mà không cần cập nhật thủ công.

### 2.2 Sheet `transactions` (vùng dùng B2:AF1226)

Sheet nhập liệu chính, chứa 3 Excel Table:

**Bảng `wallets`** (B6:D13) — Danh sách các ví/tài khoản và số dư tính động:

| Cột | Ý nghĩa |
|---|---|
| `wallet` | Tên ví: `cash`, `mb bank`, `timo`, `vietcombank`, `msb`, `shopee pay` |
| `balance` | Công thức tổng hợp: `SUMIF` giao dịch của ví đó trong `transactions[]` **+/–** chuyển khoản đi/đến trong `transfers[]` **+/–** các khoản vay/mượn/thu/trả trong `debt[]` (sheet `debts`) |
| `rate` | Tỷ trọng số dư ví này trên tổng số dư mọi ví |

Dòng `Total` dùng `SUM`/`SUBTOTAL(109,...)` để cộng tổng, bỏ qua các dòng ẩn.

**Bảng `transactions`** (B16:G1197, ~1.181 dòng) — Sổ nhật ký giao dịch:

| Cột | Ý nghĩa |
|---|---|
| `date` | Ngày giao dịch |
| `amount` | Số tiền (âm = chi, dương = thu) |
| `wallet` | Ví thực hiện giao dịch (dropdown, nguồn từ `wallets`) |
| `type` | `income` / `expense` (dropdown, nguồn từ `database.classification`) |
| `group` | Nhóm phân loại phụ thuộc `type` (dropdown động) |
| `category` | Danh mục chi tiết phụ thuộc `type` + `group` (dropdown động) |

Cột `type`, `group`, `category` được **sinh dropdown động bằng VBA** (xem mục 4 – module `modDropdown`), lấy dữ liệu từ bảng `classification` ở sheet `database`.

**Bảng `transfers`** (B1200:E1226) — Giao dịch chuyển tiền nội bộ giữa các ví (`date`, `amount`, `from`, `to`); dùng để cộng/trừ vào `wallets[balance]` mà không tính là thu/chi thật.

### 2.3 Sheet `debts` (vùng dùng B2:H15)

Quản lý công nợ (cho vay / đi vay):

- **B2/D2:E3** – Tổng hợp: `receivable` (phải thu = lend – collect – write-off lend) và `payable` (phải trả = borrow – repay – write-off borrow), tính bằng `SUMIF` trên bảng `debt`.
- **PivotTable `acounts_statement`** (B6:C8) – Sao kê công nợ theo từng người (account), tổng hợp `amount` theo tên; nguồn là bảng `debt`.
- **Bảng `debt`** (B14:H15) – Nhật ký công nợ, cột: `date`, `batch` (mã đợt vay), `person`, `type` (`lend`/`borrow`/`collect`/`repay`/`write-off lend`/`write-off borrow`), `amount`, `wallet`, `note`.

### 2.4 Sheet `finance` (vùng dùng A2:L84)

Sheet quản lý tiết kiệm, quỹ đầu tư và các "quỹ mục tiêu":

**Bảng `funds`** (B6:K11) — Tổng hợp theo từng quỹ mục tiêu (`emergency`, `health`, `target`, `retirement`):
- `cash` (tra cứu từ `funds_cash`), `saving` (`SUMIFS` từ bảng `savings`), `fund cert` (`SUMIFS` từ bảng `fund_certs`), `total` = tổng 3 cấu phần, `target` (số tiền mục tiêu, nhập tay), `process` = `total/target` (tiến độ %).

**Bảng `funds_cash`** (B14:D19) — Phần tiền mặt/tiền gửi thanh khoản phân bổ cho từng quỹ (`fund`, `amount`, `rate` = tỷ trọng).

**Bảng `savings`** (B42:I55) — Sổ tiết kiệm có kỳ hạn:

| Cột | Ý nghĩa |
|---|---|
| `fund` | Thuộc quỹ mục tiêu nào |
| `principal` / `capital` | Vốn gốc |
| `term` | Kỳ hạn (tra `database.term` để lấy số ngày) |
| `rate` | Lãi suất năm |
| `start` | Ngày gửi |
| `process` | Tiến độ kỳ hạn: `1` nếu "unlimited" (không kỳ hạn), ngược lại = số ngày đã gửi / số ngày kỳ hạn |
| `balance` | Số dư gồm gốc + lãi tích lũy: nếu không kỳ hạn thì lãi đơn `capital*rate/365`; có kỳ hạn thì tính theo số ngày thực gửi |

**Bảng `fund_certs`** (B58:I84) — Chứng chỉ quỹ/cổ phiếu đầu tư:

| Cột | Ý nghĩa |
|---|---|
| `fund` | Quỹ mục tiêu sở hữu |
| `name` | Mã quỹ/chứng chỉ (VD: DCDS, SSISCA, VESAF...) |
| `type` | `stock` / `bond` / `balance` (tra `database.Type`) |
| `principal` | Vốn gốc đầu tư |
| `start` | Ngày mua |
| `rate` | Suất sinh lời quy đổi năm, tính bằng hàm tài chính `RATE()` dựa trên số ngày nắm giữ, vốn gốc và giá trị hiện tại |
| `interest` | Lãi/lỗ = `balance - principal` |
| `balance` | Giá trị hiện tại (nhập tay theo giá thị trường) |

**5 Chart** (chart14–chart18) minh họa cơ cấu quỹ theo loại tài sản, tiến độ mục tiêu, biến động giá trị theo thời gian.

### 2.5 Sheet `database` (vùng dùng B2:S58)

Sheet **danh mục tham chiếu (master data)**, không có giao dịch phát sinh, dùng làm nguồn cho dropdown và VLOOKUP toàn bộ workbook:

| Bảng | Vị trí | Nội dung |
|---|---|---|
| `classification` | B6:D34 | Danh mục 3 cấp `type` → `group` → `category` cho thu/chi. `type=income`: nhóm `employment`, `assets`, `other`. `type=expense`: nhóm `essentials`, `personal & leisure`, `obligations & transfers`, `savings & investing` |
| `debt_type` | B36:C42 | 6 loại giao dịch công nợ: `lend` (cho vay), `borrow` (đi vay), `collect` (thu nợ), `repay` (trả nợ), `write-off lend` (xóa nợ vay), `write-off borrow` (được xóa nợ) — kèm mô tả tiếng Việt |
| `term` | B44:C53 | Bảng quy đổi kỳ hạn tiết kiệm sang số ngày (1 tháng=30, 3 tháng=90, ... 5 năm=1800, unlimited=0) |
| `fund_cert_type` | B55:B58 | 3 loại tài sản đầu tư: `stock`, `bond`, `balance` |
| `assets_total` | F6:G55 | Chuỗi thời gian tổng tài sản theo tháng (`month`, `balance`) dùng vẽ biểu đồ xu hướng — cột `month` móc nối lùi kiểu tương tự `months_stat` |
| `fund_performance` | I6:K55 | Chuỗi thời gian hiệu suất quỹ theo tháng (`months`, `principal`, `interest`) |
| Vùng M/N, P/Q (không đặt tên Table chính thức) | M5:N22, P5:Q32 | Vùng đích của 6 PivotTable còn lại: `income_group`, `income_category`, `expense_group`, `expense_category`, `fund_name_percent`, `fund_type_percent` |
| S5:S13 | Danh sách tên font chữ dùng cho định dạng/trang trí (không liên quan số liệu tài chính) |

`assets_total` và `fund_performance` là hai bảng **được VBA (module `modMonthEnd`) tự động chèn thêm dòng tháng mới** khi mở/lưu file (xem mục 4).

---

## 3. Excel Tables (ListObject) — tổng hợp

| # | Tên bảng | Sheet | Vùng | Vai trò |
|---|---|---|---|---|
| 1 | `wallets` | transactions | B6:D13 | Danh sách ví & số dư |
| 2 | `transactions` | transactions | B16:G1197 | Nhật ký thu/chi |
| 3 | `transfers` | transactions | B1200:E1226 | Chuyển khoản nội bộ |
| 4 | `debt` | debts | B14:H15 | Nhật ký công nợ |
| 5 | `savings` | finance | B42:I55 | Sổ tiết kiệm |
| 6 | `fund_certs` | finance | B58:I84 | Chứng chỉ quỹ đầu tư |
| 7 | `funds` | finance | B6:K11 | Tổng hợp theo quỹ mục tiêu |
| 8 | `funds_cash` | finance | B14:D19 | Tiền mặt phân bổ theo quỹ |
| 9 | `classification` | database | B6:D34 | Danh mục type/group/category |
| 10 | `debt_type` | database | B36:C42 | Danh mục loại công nợ |
| 11 | `term` | database | B44:C53 | Danh mục kỳ hạn |
| 12 | `fund_cert_type` | database | B55:B58 | Danh mục loại tài sản đầu tư |
| 13 | `assets_total` | database | F6:G55 | Chuỗi tổng tài sản theo tháng |
| 14 | `fund_performance` | database | I6:K55 | Chuỗi hiệu suất quỹ theo tháng |
| 15 | `months_stat` | dashboard | P29:T42 | Thống kê thu/chi 12 tháng |

Toàn bộ công thức trong workbook đều dùng **cú pháp tham chiếu Structured Reference** (`tableName[columnName]`, `[#This Row]`, `[#Totals]`) thay vì địa chỉ ô cố định — đây là quy ước xuyên suốt file.

---

## 4. PivotTable

| # | Tên Pivot | Sheet | Vùng đích | Nguồn dữ liệu | Bộ lọc |
|---|---|---|---|---|---|
| 1 | `month_exp` | dashboard | R82:T93 | `transactions` | `type=expense`, `this month=TRUE`; nhóm theo `category` |
| 2 | `month_in` | dashboard | R68:T76 | `transactions` | `type=income`, `this month=TRUE`; nhóm theo `category` |
| 3 | `acounts_statement` | debts | B6:C8 | `debt` | Tổng `amount` theo `person` |
| 4 | `income_group` | database | M10:N14 | `transactions` | `type=income`, `this month=TRUE`; nhóm theo `group` |
| 5 | `income_category` | database | P10:Q15 | `transactions` | `type=income`, `this month=TRUE`; nhóm theo `category` |
| 6 | `expense_group` | database | M19:N22 | `transactions` | `type=expense`, `this month=TRUE`; nhóm theo `group` |
| 7 | `expense_category` | database | P19:Q25 | `transactions` | `type=expense`, `this month=TRUE`; nhóm theo `category` |
| 8 | `fund_name_percent` | database | M28:N36 | `fund_certs` | Tỷ trọng `%balance` theo `name` (mã quỹ) |
| 9 | `fund_type_percent` | database | P28:Q32 | `fund_certs` | Tỷ trọng theo `type` (stock/bond/balance) |

Tất cả pivot dựa trên `PivotCache` trỏ về các bảng đã liệt kê ở mục 3; khi dữ liệu gốc thay đổi, pivot cần **Refresh** để cập nhật (việc này được tự động hoá bằng VBA — xem `modRefresh`).

## 5. Chart (biểu đồ)

| # | Sheet | Loại | Tiêu đề / nội dung |
|---|---|---|---|
| chart1 | dashboard | Doughnut | Cơ cấu tài sản (không tiêu đề hiển thị) |
| chart2 | dashboard | Doughnut | Cơ cấu quỹ mục tiêu |
| chart3 | dashboard | Line | "cash" – biến động số dư ví theo thời gian |
| chart4 | dashboard | Doughnut | Cơ cấu ví |
| chart5 | dashboard | Line | Xu hướng cash flow theo tháng |
| chart6 | dashboard | Bar | "income by group" |
| chart7 | dashboard | Bar | "expense by group" |
| chart8 | dashboard | Bar | "income by category" |
| chart9 | dashboard | Bar | "expense by category" |
| chart10 | dashboard | Bar | "income & expense" (so sánh thu/chi theo tháng) |
| chart11 | dashboard | Bar | "funds" (số dư từng quỹ) |
| chart12 | dashboard | Bar | "funds process" (tiến độ mục tiêu) |
| chart13 | dashboard | Line | "fund" (biến động quỹ theo thời gian) |
| chart14 | finance | Doughnut | "type of fund" (cơ cấu theo loại tài sản) |
| chart15 | finance | Bar | "funds process" |
| chart16 | finance | Bar | "funds" |
| chart17 | finance | Bar | "funds" |
| chart18 | finance | Doughnut | "fund" |

---

## 6. Thành phần VBA (Macro)

File dùng VBA để **tự động hoá 4 việc**: tạo dropdown phân cấp động, giữ khoảng trống cuối bảng khi thêm dòng, tự chốt/khởi tạo dòng tháng mới cho các bảng chuỗi thời gian, và tự refresh Pivot/Filter/tính toán mỗi khi mở hoặc lưu file.

### 6.1 `ThisWorkbook` (code-behind Workbook)

```vb
Private Sub Workbook_SheetChange(ByVal Sh As Object, ByVal Target As Range)
    OnCellChange Sh, Target
End Sub

Private Sub Workbook_Open()
    RunRefresh
End Sub

Private Sub Workbook_BeforeSave(ByVal SaveAsUI As Boolean, Cancel As Boolean)
    RunRefresh
End Sub
```

- Khi **mở file** hoặc **trước khi lưu**: tự động gọi `RunRefresh` (module `modRefresh`) để refresh toàn bộ PivotTable, áp lại AutoFilter và tính lại toàn bộ công thức.
- Khi bất kỳ ô nào trên bất kỳ sheet nào thay đổi: gọi `OnCellChange` — tuy nhiên hàm `OnCellChange` không tồn tại trực tiếp trong `ThisWorkbook` mà được suy ra ngầm định là gọi hàm cùng tên `Public` trong module khác (thực tế logic dropdown chính được gán trực tiếp ở sự kiện `Worksheet_Change` của `Sheet2`, xem bên dưới).

> **Lưu ý:** Dòng `OnCellChange Sh, Target` trong `Workbook_SheetChange` không có `modXXX.` phía trước; nếu không có hàm `Public Sub OnCellChange` nào ở module mặc định (không phải `modDropdown.OnCellChange`, vì hàm đó nhận tham số `ws As Worksheet` — kiểu tham số của `Workbook_SheetChange` là `Sh As Object`), dòng này sẽ gây lỗi biên dịch nếu Excel không tự phân giải được. Trên thực tế phần xử lý dropdown "thực sự chạy" là qua sự kiện cấp Sheet (`Sheet2.Worksheet_Change`) bên dưới.

### 6.2 `Sheet2` (= sheet `transactions`, code-behind Worksheet)

```vb
Private Sub Worksheet_Change(ByVal Target As Range)
    modDropdown.OnCellChange Me, Target
    modLayout.MaintainSpacing Me
End Sub

Private Sub Worksheet_SelectionChange(ByVal Target As Range)
    modLayout.MaintainSpacing Me
End Sub
```

Đây là nơi thực sự kích hoạt 2 module chính mỗi khi người dùng gõ vào sheet `transactions`:
1. `modDropdown.OnCellChange` – cập nhật dropdown `group`/`category` theo `type` vừa chọn.
2. `modLayout.MaintainSpacing` – tự chèn thêm 1 dòng trống ngay dưới bảng `transactions` nếu dòng kế tiếp bảng đã có dữ liệu (giữ bảng luôn có khoảng đệm, tránh việc gõ đè lên dữ liệu bên dưới bảng).

Các `Sheet1`, `Sheet3`, `Sheet4`, `Sheet6` (dashboard, debts, finance, và 1 sheet ẩn/đã xoá) không chứa code — chỉ khai báo thuộc tính module trống.

### 6.3 Module `modDropdown` — Dropdown phân cấp (Type → Group → Category)

**Mục đích:** Khi người dùng chọn `type` (income/expense) ở bảng `transactions`, tự động sinh danh sách xổ xuống cho `group` phù hợp; khi chọn `group`, tự sinh danh sách `category` phù hợp — dữ liệu lấy từ bảng `classification` trên sheet `database`.

**Hằng số cấu hình:**
```vb
Private Const C_TRANS_SHEET As String = "transactions"
Private Const C_DB_SHEET    As String = "database"
Private Const C_COL_TYPE    As String = "type"
Private Const C_COL_GROUP   As String = "group"
Private Const C_COL_CAT     As String = "category"
Private Const C_HDR_LIMIT   As Long = 30   ' chỉ tìm header trong 30 dòng đầu
```

**Luồng xử lý `OnCellChange(ws, Target)`:**
1. Thoát ngay nếu sheet đang sửa không phải `transactions`, hoặc người dùng sửa nhiều hơn 1 ô cùng lúc.
2. Tìm dòng header (dòng chứa cột `type`) bằng `FindHeader`; bỏ qua nếu ô sửa nằm trên/tại header.
3. Xác định cột `type`, `group`, `category` bằng `ColOf` (dò theo tên cột, không cố định vị trí).
4. Chỉ xử lý tiếp nếu ô vừa sửa nằm ở cột `type` hoặc `group`.
5. Tắt `Application.EnableEvents` để tránh đệ quy vô hạn (vì thao tác ghi validation cũng kích hoạt `Worksheet_Change`).
6. Nếu sửa cột `type` → gọi `SetGroupDropdown`: xoá `group`/`category` hiện tại, quét toàn bộ bảng `classification` để lấy danh sách `group` **duy nhất** ứng với `type` vừa chọn (dùng `Scripting.Dictionary` để loại trùng), rồi gán làm Data Validation List cho ô `group` cùng dòng.
7. Nếu sửa cột `group` → gọi `SetCatDropdown`: tương tự nhưng lọc theo cả `type` **và** `group` để lấy danh sách `category`, gán cho ô `category` cùng dòng.
8. Khối `Cleanup` luôn bật lại `EnableEvents = True` dù có lỗi hay không.

**Các hàm phụ trợ:**
- `FindHeader(ws, keyword)`: dùng `Range.Find` (không phân biệt hoa/thường, khớp toàn bộ ô) để tìm dòng tiêu đề chứa từ khoá, trong giới hạn 30 dòng đầu.
- `ColOf(rng, keyword)`: duyệt từng ô trong dòng header để tìm cột có tên khớp (so sánh không phân biệt hoa/thường, đã `Trim`).
- `HeadRange(ws, hdrRow)`: trả về toàn bộ dòng header từ cột A đến cột cuối có dữ liệu.
- `LastDataRow(ws)`: tìm dòng cuối cùng có dữ liệu trên sheet (`Cells.Find("*", SearchDirection:=xlPrevious)`).
- `ApplyValidation(cell, listStr)`: xoá validation cũ, tạo validation kiểu `List` mới (`xlValidateList`) với chuỗi các giá trị nối bằng dấu phẩy, cho phép ô trống, hiển thị dropdown trong ô.

### 6.4 Module `modLayout` — Duy trì khoảng đệm cuối bảng

```vb
Private Const C_TRANS_SHEET As String = "transactions"
Private Const C_TBL_TRANS   As String = "transactions"

Public Sub MaintainSpacing(ws As Worksheet)
    ' Thoát nếu không phải sheet transactions hoặc không tìm thấy bảng "transactions"
    ' Tính dòng ngay dưới bảng (rowBelow)
    ' Nếu dòng đó có bất kỳ ô nào chứa dữ liệu (CountA > 0):
    '     chèn 1 dòng trống mới tại đó (Insert Shift:=xlDown)
End Sub
```

Cơ chế: mỗi khi người dùng gõ hoặc chỉ đổi vùng chọn (`Worksheet_SelectionChange`) trên sheet `transactions`, hàm này kiểm tra dòng ngay dưới bảng `transactions` (ListObject). Nếu dòng đó bắt đầu có dữ liệu (nghĩa là bảng sắp "đụng" vào nội dung phía dưới, ví dụ bảng `transfers`), nó tự chèn thêm 1 dòng trống để giữ khoảng cách — giúp bảng luôn có "vùng đệm" an toàn khi Excel tự mở rộng ListObject lúc gõ dòng mới.

### 6.5 Module `modMonthEnd` — Tự động chốt số & thêm dòng tháng mới

Áp dụng cho 2 bảng chuỗi thời gian trên sheet `database`: `assets_total` (tổng tài sản theo tháng) và `fund_performance` (hiệu suất quỹ theo tháng).

```vb
Private Const C_DB_SHEET   As String = "database"
Private Const C_TBL_ASSETS As String = "assets_total"
Private Const C_TBL_FUND   As String = "fund_performance"

Public Sub MonthEndFreeze()
    ' Tắt ScreenUpdating, chuyển Calculation sang Manual để chạy nhanh
    ' Gọi HandleTable cho từng bảng ở trên
    ' Bật lại Calculation Automatic + ScreenUpdating
End Sub
```

**`HandleTable(ws, tblName)`** — logic cốt lõi cho mỗi bảng:
1. Lấy dòng cuối cùng (`ListRow` cuối) của bảng.
2. So sánh tháng của dòng cuối (`lastMon`, lấy từ cột đầu tiên) với tháng hiện tại của hệ thống (`curMon`).
3. Nếu dòng cuối **đã** ở đúng tháng hiện tại **và** đã có công thức ở các cột khác (`IsDone` = True) → không làm gì (đã xử lý rồi).
4. Ngược lại:
   - Lưu lại toàn bộ công thức của dòng cuối (từ cột 2 trở đi) vào mảng `formulas()`.
   - Nếu tháng dòng cuối **khác** tháng hiện tại (`lastMon <> curMon`) → gọi `FreezeRow`: ghi đè từng ô công thức bằng chính giá trị hiện tại của nó (`Value2 = Value2`), tức **"đóng băng"** (chuyển từ formula thành số cứng) dòng tháng cũ, để dữ liệu lịch sử không đổi khi các tham chiếu thay đổi sau này.
   - Gọi `AppendMonth`: thêm 1 `ListRow` mới vào cuối bảng, cột đầu tiên đặt công thức tăng thêm 1 tháng so với dòng trước (`=DATE(YEAR(...),MONTH(...)+1,1)`), các cột còn lại copy lại đúng công thức đã lưu ở bước trên (để dòng mới tiếp tục tính động cho tới lượt "đóng băng" kế tiếp).
5. `IsDone(row, lastMon, curMon)`: trả `True` chỉ khi tháng trùng nhau **và** dòng đó vẫn còn ít nhất 1 công thức (chưa bị đóng băng) — dùng để tránh chèn dòng trùng lặp trong cùng một tháng.

> **Ghi chú:** `MonthEndFreeze` là `Public Sub` nhưng **không được gọi tự động** ở đâu trong các module còn lại (không xuất hiện trong `RunRefresh`, `Workbook_Open`...). Đây nhiều khả năng là macro chạy **thủ công** (ví dụ gán vào nút bấm hoặc phím tắt) vào cuối mỗi tháng để chốt số liệu tài sản/hiệu suất quỹ.

### 6.6 Module `modRefresh` — Tự động làm mới dữ liệu

```vb
Sub RunRefresh()
    ' Tắt ScreenUpdating & EnableEvents
    RefreshPivots      ' làm mới toàn bộ PivotTable trên mọi sheet
    ReapplyFilters     ' áp lại AutoFilter (nếu có) trên mọi sheet
    Application.CalculateFull   ' tính lại toàn bộ công thức trong workbook
    ' Bật lại ScreenUpdating & EnableEvents (kể cả khi có lỗi, nhờ On Error GoTo Cleanup)
End Sub

Sub RefreshPivots()
    ' Duyệt qua mọi Worksheet, mọi PivotTable trong đó, gọi pt.RefreshTable
End Sub

Sub ReapplyFilters()
    ' Duyệt qua mọi Worksheet có AutoFilterMode = True, gọi ws.AutoFilter.ApplyFilter
End Sub
```

`RunRefresh` được gọi tự động ở 2 thời điểm (qua `ThisWorkbook`): **khi mở file** (`Workbook_Open`) và **ngay trước khi lưu file** (`Workbook_BeforeSave`) — đảm bảo mọi PivotTable, bộ lọc và công thức luôn phản ánh đúng dữ liệu mới nhất trước khi người dùng đóng/lưu file, kể cả khi họ quên bấm "Refresh All" thủ công.

### 6.7 Tổng kết luồng sự kiện VBA

| Sự kiện | Điều kiện | Hành động |
|---|---|---|
| Mở workbook | Luôn | `RunRefresh` → refresh mọi Pivot, áp lại filter, tính lại toàn bộ công thức |
| Trước khi Save | Luôn | `RunRefresh` (như trên) |
| Sửa 1 ô trên sheet `transactions`, cột `type` | Đúng 1 ô, đúng cột | Sinh lại dropdown `group` theo `type` |
| Sửa 1 ô trên sheet `transactions`, cột `group` | Đúng 1 ô, đúng cột | Sinh lại dropdown `category` theo `type` + `group` |
| Sửa/chọn ô bất kỳ trên sheet `transactions` | Bảng `transactions` đã "chạm" dòng dữ liệu bên dưới | Tự chèn thêm dòng trống đệm |
| Chạy thủ công `MonthEndFreeze` | Cuối tháng | Đóng băng số liệu tháng cũ (chuyển formula → giá trị) và thêm dòng tháng mới cho `assets_total`, `fund_performance` |

---

## 7. Logic tính toán nổi bật (công thức)

- **Số dư ví (`wallets[balance]`):** cộng dồn `transactions` của ví đó, cộng/trừ `transfers` đến/đi, cộng/trừ các nghiệp vụ công nợ liên quan đến ví đó (vay vào ví = cộng, trả nợ từ ví = trừ, cho vay từ ví = trừ, thu nợ về ví = cộng). Đây là công thức dài nhất trong file, lặp lại cho từng dòng của bảng `wallets`.
- **Phải thu / phải trả (`debts` & `dashboard`):** `receivable = lend – collect – write-off lend`; `payable = borrow – repay – write-off borrow`.
- **Lãi tiết kiệm (`savings[balance]`):** phân biệt 2 trường hợp — kỳ hạn "unlimited" tính lãi đơn theo ngày thực tế trôi qua (`capital*rate/365`), có kỳ hạn cố định thì nhân thêm hệ số **số ngày đã gửi/số ngày kỳ hạn** rồi cộng gốc.
- **Suất sinh lời quỹ đầu tư (`fund_certs[rate]`):** dùng hàm tài chính `RATE()` với số kỳ = số năm nắm giữ (`(TODAY()-start)/365.25`), giá trị đầu tư (PV âm), giá trị hiện tại (FV) — quy đổi ra lãi suất kép hàng năm.
- **Chuỗi thời gian tự trượt (`months_stat`, `assets_total`, `fund_performance`):** mỗi dòng tính lùi 1 tháng từ dòng phía dưới nó bằng `DATE(YEAR(x),MONTH(x)±1,1)`, dòng neo (đầu hoặc cuối chuỗi) gắn với `TODAY()` — nên toàn bảng tự dịch chuyển theo ngày hệ thống mà không cần sửa tay.
- **Tiến độ mục tiêu quỹ (`funds[process]`):** `total/target` (có `IFERROR`/`IF` chặn chia cho 0 khi `target=0`).

---

## 8. Named Ranges / kết nối ngoài

Workbook còn sót lại một số **Defined Name** liên quan tới các **kết nối dữ liệu Power Query cũ** (loại `WorksheetConnection`), có khả năng là tàn dư từ các phiên bản file trước (`Finance6.9.xlsx`, `Financialv1.1.xlsx`):

- `debt[]`, `fund_certs[]`, `transactions[]` (từ `Finance6.9.xlsx`) — vẫn còn tham chiếu hợp lệ tới bảng cùng tên trong file hiện tại.
- `SPT`, `SPT_8`, `Table8` (từ `Financialv1.1.xlsx`) — đều đã **hỏng** (`#REF!`), là tham chiếu treo (broken reference) tới workbook nguồn cũ không còn liên kết. Không ảnh hưởng tới việc tính toán hiện tại nhưng có thể gây cảnh báo "external link" khi mở file.

---

## 9. Tóm tắt các điểm cần lưu ý khi chỉnh sửa file

1. **Không sửa trực tiếp công thức trong các bảng `wallets`, `funds`, `savings`, `fund_certs`, `months_stat`** — đây là vùng tính toán, chỉ nên sửa các ô "input" (ví dụ: `target` trong `funds`, `principal`/`capital`/`rate`/`start` trong `savings`, `principal`/`start`/`balance` trong `fund_certs`).
2. **Nhập giao dịch mới ở sheet `transactions`:** chỉ cần gõ `date`, `amount`, `wallet`, `type` — cột `group`/`category` sẽ tự có dropdown đúng ngữ cảnh nhờ VBA; nếu tắt Macro, các dropdown động này sẽ **không hoạt động** (phải chọn thủ công hoặc gõ tay).
3. **Việc thêm/sửa danh mục phân loại (type/group/category)** nên thực hiện tại bảng `classification` trên sheet `database`, vì mọi dropdown và một phần công thức phân loại đều tham chiếu tới bảng này.
4. **Macro yêu cầu bật "Enable Content"** khi mở file, nếu không: dropdown động, tự refresh pivot khi mở/lưu, và tính năng chèn dòng đệm sẽ không chạy (dữ liệu và công thức tĩnh vẫn hiển thị bình thường).
5. **`MonthEndFreeze` (module `modMonthEnd`) không tự chạy** — cần gán vào nút/phím tắt hoặc chạy thủ công từ VBA Editor (Alt+F11) vào cuối mỗi tháng để chốt số liệu `assets_total`/`fund_performance`.
6. File còn tồn tại **liên kết ngoài hỏng** (`SPT`, `SPT_8`, `Table8`) từ phiên bản cũ (`Financialv1.1.xlsx`) — có thể dọn dẹp qua **Data → Edit Links** nếu muốn loại bỏ cảnh báo.
