package com.pandey.models;

import com.pandey.constants.Color;
import com.pandey.constants.PieceType;
import com.pandey.exceptions.InvalidMoveException;
import com.pandey.models.moveValidators.MoveValidator;
import com.pandey.models.moveValidators.MoveValidatorFactory;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Board {
    List<Player> players = new ArrayList<>();
    List<List<Piece>> board = new ArrayList<>(8);
    Integer moveNumber = 1;

    public Board() {
        for (int i = 0; i < 8; i++) {
            List<Piece> row = new ArrayList<>(8);
            for (int j = 0; j < 8; j++) {
                row.add(null);
            }
            board.add(row);
        }
        initializeChessPieces();
    }

    private void initializeChessPieces() {
        board.get(0).set(0, new Piece(Color.WHITE, PieceType.ROOK));
        board.get(0).set(1, new Piece(Color.WHITE, PieceType.KNIGHT));
        board.get(0).set(2, new Piece(Color.WHITE, PieceType.BISHOP));
        board.get(0).set(3, new Piece(Color.WHITE, PieceType.QUEEN));
        board.get(0).set(4, new Piece(Color.WHITE, PieceType.KING));
        board.get(0).set(5, new Piece(Color.WHITE, PieceType.BISHOP));
        board.get(0).set(6, new Piece(Color.WHITE, PieceType.KNIGHT));
        board.get(0).set(7, new Piece(Color.WHITE, PieceType.ROOK));
        for (int i = 0; i < 8; i++) {
            board.get(1).set(i, new Piece(Color.WHITE, PieceType.PAWN));
        }

        board.get(7).set(0, new Piece(Color.BLACK, PieceType.ROOK));
        board.get(7).set(1, new Piece(Color.BLACK, PieceType.KNIGHT));
        board.get(7).set(2, new Piece(Color.BLACK, PieceType.BISHOP));
        board.get(7).set(3, new Piece(Color.BLACK, PieceType.QUEEN));
        board.get(7).set(4, new Piece(Color.BLACK, PieceType.KING));
        board.get(7).set(5, new Piece(Color.BLACK, PieceType.BISHOP));
        board.get(7).set(6, new Piece(Color.BLACK, PieceType.KNIGHT));
        board.get(7).set(7, new Piece(Color.BLACK, PieceType.ROOK));
        for (int i = 0; i < 8; i++) {
            board.get(6).set(i, new Piece(Color.BLACK, PieceType.PAWN));
        }
    }
    public void move(Move move) throws InvalidMoveException {
        Color playerColor = moveNumber%2 == 0 ? Color.BLACK : Color.WHITE;
        Piece movePiece = board.get(move.startX).get(move.startY);
        Piece endPiece = board.get(move.endX).get(move.endY);
        if(movePiece == null || movePiece.getColor() != playerColor)
            throw new InvalidMoveException();
        if(endPiece != null && endPiece.getColor() == playerColor)
            throw new InvalidMoveException();
        MoveValidator moveValidator = MoveValidatorFactory.createValidator(movePiece.getPieceType());
        moveValidator.validate(move, this);
        board.get(move.endX).set(move.endY, movePiece);
        board.get(move.startX).set(move.startY, null);
        moveNumber++;
    }
    public void print() {
        System.out.println();
        for(int i = 7; i >= 0; --i) {
            var row = board.get(i);
            for(Piece piece: row) {
                if(piece == null) {
                    System.out.print("--\t");
                    continue;
                }
                String output = piece.getColor().getNotation() + piece.getPieceType().getNotation();
                System.out.print(output + "\t");
            }
            System.out.println();
        }
    }
    public void addPlayer(Player player) {
        if(players.size() >= 2)
            System.out.println("Only two players can play");
        player.setId(players.size()+1);
        players.add(player);
    }
}
