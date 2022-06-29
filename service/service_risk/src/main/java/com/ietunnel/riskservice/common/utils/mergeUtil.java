package com.ietunnel.riskservice.common.utils;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import java.util.ArrayList;

public class mergeUtil {
    // word表格跨行并单元格
    //col 指定列、fromRow 开始行、toRow 结束行。
    public void mergeColumn(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            // 表格居中
            CTJc jc = table.getCTTbl().getTblPr().getJc();
            if(jc == null){
                jc = table.getCTTbl().getTblPr().addNewJc();
            }
            jc.setVal(STJc.CENTER);
            table.getCTTbl().getTblPr().setJc(jc);
            // 文字居中
            cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

            if (rowIndex == fromRow) {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }


    // word表格跨列合并单元格
    //row 指定行、fromCell 开始列数、toCell 结束列数。
    public void mergeLine(XWPFTable table, int row, int fromCell, int toCell) {
        ArrayList<String> list = new ArrayList<>();

        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }
}
