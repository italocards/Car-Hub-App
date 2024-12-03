package com.example.carhubjava.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.carhubjava.R;
import com.example.carhubjava.model.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> carList;
    private OnCarClickListener onCarClickListener;

    public interface OnCarClickListener {
        void onCarClick(Car car);
    }

    public CarAdapter(List<Car> carList, OnCarClickListener listener) {
        this.carList = carList;
        this.onCarClickListener = listener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.nameTextView.setText(car.getName());
        holder.priceTextView.setText(car.getPricePerDay());
        Glide.with(holder.itemView.getContext()).load(car.getImageUrl()).into(holder.carImageView);

        holder.itemView.setOnClickListener(v -> onCarClickListener.onCarClick(car));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    // Método para atualizar a lista de carros
    public void setCarList(List<Car> carList) {
        this.carList = carList;
        notifyDataSetChanged();  // Notifica que a lista foi atualizada
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        ImageView carImageView;

        public CarViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.carName);
            priceTextView = itemView.findViewById(R.id.carPrice);
            carImageView = itemView.findViewById(R.id.carImage);
        }
    }
}
