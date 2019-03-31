package SmarterPOS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.util.Duration;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.controlsfx.control.Notifications;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

public class PurchasesPrinting {
	private int Case;
	private String Total;
	private String Date;
	private String customer;
	private String id;
	private String returnID;
	String label;

	public PurchasesPrinting(int status, String label, String Date, String customer, String id, String returnID, String Total) {

		this.Case = status;
		this.Date = Date;
		this.customer = customer;
		this.id = id;
		this.returnID = returnID;
		this.Total = Total;
		this.label = label;
		build();
	}

	// --------------------------------------------------------- Building Report ----------------------------------
	private void build() {


		try {
			//------------------------------- ------------------ shows report title -------------------------------

			report().setPageFormat(420, 595, PageOrientation.PORTRAIT)
					.setTemplate(Templates.reportTemplate)
					.title(
							cmp.horizontalList()
									.add(
											cmp.image(getClass().getResourceAsStream("/images/invoicePrintLabel.png")).setFixedDimension(60, 60),
											cmp.text(label).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
											cmp.text("رقم الفاتورة " + ":" + id).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
											cmp.text("شكرا لثقتكم").setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT))

									//.newRow()
									.newFlowRow()
									.add(
											cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10)),
							cmp.horizontalList().setStyle(stl.style(10)).setGap(50)
									.add(
											cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10),
											cmp.horizontalList().setStyle(stl.style(10)).setGap(50).add(
													cmp.hListCell(createCustomerComponent()).heightFixedOnTop())
													.newRow()
													.add(cmp.text("قيمة الفاتورة " + ":" + Total).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER))
									)
					)
					.columns(
							col.column("باركود", "PBar", type.stringType()),
							col.column("الإسم", "PUniname", type.stringType()),
							col.column("الكمية", "PQTY", type.stringType()),
							col.column("إفرادي", "PPrice", type.stringType()),
							col.column("مجموع جزئي", "PSubtotal", type.stringType()),
							col.column("حسم", "PDiscount", type.stringType()),
							col.column("المجموع", "PTotal", type.stringType()))
					.detailFooter(
							cmp.horizontalList(cmp.horizontalGap(150), cmp.horizontalGap(150)),
							cmp.line())
					.pageFooter(Templates.footerComponent)
					.setDataSource(createDataSource())
					.show(false);
		} catch (Exception ex) {
			Notifications notify = Notifications.create()
					.title("خطا في الطباعة")
					.text("حدثت مشكلة آثناء الطباعة")
					.graphic(null)
					.hideAfter(Duration.seconds(5))
					.position(Pos.TOP_RIGHT);
			notify.show();
		}
	}

	// --------------------------------------------------------Getting Data To fill the report --------------------
	public JRDataSource createDataSource() {
		ObservableList<PurchaseRecord> data = FXCollections.observableArrayList();
		switch (Case) {
			case 1:
				data = MainPanel.Purchasesdata;
				break;
			case 2:
				data = MainPanel.ReSalesdata;
				break;
		}
		return new JRBeanCollectionDataSource(data);
	}
	//  ------------------------------------ Create User Info ---------------------------

	private ComponentBuilder<?, ?> createCustomerComponent() {
		HorizontalListBuilder list = cmp.horizontalList().setBaseStyle(stl.style().setTopBorder(stl.pen1Point()).setLeftPadding(10));
		if (Case == 2) {
			addCustomerAttribute(list, "الزبون", customer);
			addCustomerAttribute(list, "التاريخ", Date);
			addCustomerAttribute(list, "فاتورة الاصلية", returnID);
		} else {
			addCustomerAttribute(list, "الزبون", customer);
			addCustomerAttribute(list, "التاريخ", Date);
		}
		return cmp.verticalList(
				cmp.text("فاتورة بإسم").setStyle(Templates.boldStyle),
				list);
	}

	private void addCustomerAttribute(HorizontalListBuilder list, String label, String value) {
		if (value != null) {
			list.add(cmp.text(":" + label).setFixedColumns(10).setStyle(Templates.boldStyle), cmp.text(value)).newRow();
		}
	}
}

