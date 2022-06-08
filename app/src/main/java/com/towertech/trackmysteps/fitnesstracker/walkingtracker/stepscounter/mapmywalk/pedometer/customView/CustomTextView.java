package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.customView;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;


public class CustomTextView extends AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void bindText(LifecycleOwner lifecycleOwner, DefaultLiveData<String> defaultLiveData) {
        if (defaultLiveData.getValue() != null) {
            setText(defaultLiveData.getValue());
        }
        defaultLiveData.observe(lifecycleOwner, new Observer() { // from class: com.kewitschka.stepcounter.bindings.-$$Lambda$Rizmt-65auPZ25gILsSHATUYulo
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CustomTextView.this.setText((String) obj);
            }
        });
    }

    public void bindText(LifecycleOwner lifecycleOwner, DefaultLiveData<String> defaultLiveData, final BindingTransformer<String> bindingTransformer) {
        defaultLiveData.observe(lifecycleOwner, new Observer() { // from class: com.kewitschka.stepcounter.bindings.-$$Lambda$BindingTextView$dMiIK1souM3UmG3Mz4Ni5PojBuE
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CustomTextView.this.lambda$bindText$0$BindingTextView(bindingTransformer, (String) obj);
            }
        });
    }

    public  void lambda$bindText$0$BindingTextView(BindingTransformer bindingTransformer, String str) {
        if (str != null) {
            setText((CharSequence) bindingTransformer.transform(str));
        }
    }
}
