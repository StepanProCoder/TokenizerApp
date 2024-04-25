package com.staple.tokenizerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    DraggableCoordinatorLayout parentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentContainer = findViewById(R.id.parentCoordinatorLayout);
        MaterialCardView draggableCard1 = findViewById(R.id.draggableCard1);
        MaterialCardView draggableCard2 = findViewById(R.id.draggableCard2);
        MaterialCardView draggableCard3 = findViewById(R.id.draggableCard3);
        MaterialCardView draggableCard4 = findViewById(R.id.draggableCard4);

        parentContainer.addDraggableChild(draggableCard1);
        parentContainer.addDraggableChild(draggableCard2);
        parentContainer.addDraggableChild(draggableCard3);
        parentContainer.addDraggableChild(draggableCard4);

        parentContainer.setViewDragListener(new DraggableCoordinatorLayout.ViewDragListener() {
            @Override
            public void onViewCaptured(View view, int i) {
                Integer id = view.getId();
                if(id == R.id.draggableCard1)
                {
                    draggableCard1.setDragged(true);
                }
                else if(id == R.id.draggableCard2)
                {
                    draggableCard2.setDragged(true);
                }
                else if(id == R.id.draggableCard3)
                {
                    draggableCard3.setDragged(true);
                }
                else if(id == R.id.draggableCard4)
                {
                    draggableCard4.setDragged(true);
                }
            }

            @Override
            public void onViewReleased(View view, float v, float v1) {
                Integer id = view.getId();
                if(id == R.id.draggableCard1)
                {
                    draggableCard1.setDragged(true);
                }
                else if(id == R.id.draggableCard2)
                {
                    draggableCard2.setDragged(true);
                }
                else if(id == R.id.draggableCard3)
                {
                    draggableCard3.setDragged(true);
                }
                else if(id == R.id.draggableCard4)
                {
                    draggableCard4.setDragged(true);
                }
            }
        });
    }
}

