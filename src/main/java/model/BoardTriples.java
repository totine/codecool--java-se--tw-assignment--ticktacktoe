package model;

public class BoardTriples {
        private Cell[][] cells;
        private int cellsSize;

        BoardTriples(Cell[][] cells, int cellsSize) {
            this.cells = cells;
            this.cellsSize = cellsSize;
        }

        Seed[] getRow(int row) {
            Seed[] seedRow = new Seed[cellsSize];
            for (int i=0; i<cellsSize; i++) {
                seedRow[i] = cells[row-1][i].getContent();
            }
            return seedRow;
        }

        Seed[] getCol(int col) {
            Seed[] seedCol = new Seed[cellsSize];
            for (int i=0; i<cellsSize; i++) {
                seedCol[i] = cells[i][col-1].getContent();
            }
            return seedCol;
        }

        Seed[] getDiagFromLeftUp()  {
            Seed[] diag1 = new Seed[cellsSize];
            for (int i=0; i<cellsSize; i++) {
                diag1[i] = cells[i][i].getContent();
            }
            return diag1;
        }

        Seed[] getDiagFromLeftDown()  {
            Seed[] diag2 = new Seed[cellsSize];
            for (int i=0; i<3; i++) {
                diag2[i] = cells[i][(cellsSize-1)-i].getContent();
            }
            return diag2;
        }
    }