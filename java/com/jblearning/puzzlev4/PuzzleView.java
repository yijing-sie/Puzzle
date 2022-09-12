package com.jblearning.puzzlev4;

import java.util.Random;
import android.app.Activity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;

public class PuzzleView extends RelativeLayout {
  private TextView [] tvs;
  private LayoutParams [] params;
  private int [] colors;

  private int labelHeight;
  private int startY; // start y coordinate of TextView being moved
  private int startTouchY; // start y coordinate of current touch
  private int emptyPosition;
  private int [] positions;

  public PuzzleView( Activity activity, int width, int height,
                     int numberOfPieces ) {
    super( activity );
    buildGuiByCode( activity, width, height, numberOfPieces );
  }

  public void buildGuiByCode( Activity activity, int width, int height,
                              int numberOfPieces ) {
    positions = new int[numberOfPieces];
    tvs = new TextView[numberOfPieces];
    colors = new int[tvs.length];
    params = new LayoutParams[tvs.length];
    Random random = new Random( );
    labelHeight = height / numberOfPieces;
    for( int i = 0; i < tvs.length; i++ ) {
      tvs[i] = new TextView( activity );
      tvs[i].setGravity( Gravity.CENTER );
      colors[i] = Color.rgb( random.nextInt( 255 ),
        random.nextInt( 255 ),	random.nextInt( 255 ) );
      tvs[i].setBackgroundColor( colors[i] );
      params[i] = new LayoutParams( width, labelHeight );
      params[i].leftMargin = 0;
      params[i].topMargin = labelHeight * i;
      addView( tvs[i], params[i] );
    }
  }

  public void fillGui( String [] scrambledText ) {
    int minFontSize = DynamicSizing.MAX_FONT_SIZE;
    for( int i = 0; i < tvs.length; i++ ) {
      tvs[i].setText( scrambledText[i] );
      positions[i] = i;

      tvs[i].setWidth( params[i].width );
      tvs[i].setPadding( 20, 5, 20, 5 );

      // find font size dynamically
      int fontSize = DynamicSizing.setFontSizeToFitInView( tvs[i] );
      if( minFontSize > fontSize )
        minFontSize = fontSize;
    }
    Log.w("MainActivity", "font size = " + minFontSize);
    // set font size for TextViews
    for( int i = 0; i < tvs.length; i++ )
      tvs[i].setTextSize( TypedValue.COMPLEX_UNIT_SP, minFontSize );
  }

  // Returns the index of tv within the array tvs
  public int indexOfTextView( View tv ) {
    if( ! ( tv instanceof TextView ) )
      return -1;
    for( int i = 0; i < tvs.length; i++ ) {
      if( tv == tvs[i] )
        return i;
    }
    return -1;
  }

  public void updateStartPositions( int index, int y ) {
    startY = params[index].topMargin;
    startTouchY = y;
    emptyPosition = tvPosition( index );
  }

  // moves the TextView at index index
  public void moveTextViewVertically( int index, int y ) {
    params[index].topMargin = startY + y - startTouchY;
    tvs[index].setLayoutParams( params[index] );
  }

  public void enableListener( OnTouchListener listener ) {
    for( int i = 0; i < tvs.length; i++ )
      tvs[i].setOnTouchListener( listener );
  }

  public void disableListener( ) {
    for( int i = 0; i < tvs.length; i++ )
      tvs[i].setOnTouchListener( null );
  }

  // Returns position index within screen of TextField at index tvIndex
  // Accuracy is half a TextView's height
  public int tvPosition( int tvIndex ) {
    return ( params[tvIndex].topMargin + labelHeight/2 ) / labelHeight;
  }

  // Swaps tvs[tvIndex] and tvs[positions[toPosition]]
  public void placeTextViewAtPosition( int tvIndex, int toPosition ) {
    // Move current TextView to position position
    params[tvIndex].topMargin = toPosition * labelHeight;
    tvs[tvIndex].setLayoutParams( params[tvIndex] );

    // Move TextView just replaced to empty spot
    int index = positions[toPosition];
    params[index].topMargin = emptyPosition * labelHeight;
    tvs[index].setLayoutParams( params[index] );

    // Reset positions values
    positions[emptyPosition] = index;
    positions[toPosition] = tvIndex;
  }

  // Returns the current user solution as an array of Strings
  public String [] currentSolution( ) {
    String [] current = new String[tvs.length];
    for( int i = 0; i < current.length; i++ )
      current[i] = tvs[positions[i]].getText( ).toString( );

    return current;
  }

  // returns index of TextView whose location includes y
  public int indexOfTextView( int y ) {
    int position = y / labelHeight;
    return positions[position];
  }

  // returns text inside TextView whose index is tvIndex
  public String getTextViewText( int tvIndex ) {
    return tvs[tvIndex].getText( ).toString( );
  }

  // replace text inside TextView whose index is tvIndex with s
  public void setTextViewText( int tvIndex, String s ) {
    tvs[tvIndex].setText( s );
  }
}