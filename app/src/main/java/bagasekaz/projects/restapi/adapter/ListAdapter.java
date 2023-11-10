package bagasekaz.projects.restapi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import bagasekaz.projects.restapi.DetailActivity;
import bagasekaz.projects.restapi.R;
import bagasekaz.projects.restapi.model.Recruitment;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private final List<Recruitment> listRecuitment;

    public ListAdapter(List<Recruitment> listRecuitment) {
        this.listRecuitment = listRecuitment;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_job_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        Recruitment list = listRecuitment.get(position);

        holder.setImageFood(list.getCompany_logo());
        holder.tvTitle.setText(list.getTitle());
        holder.tvCompany.setText(list.getCompany());
        holder.tvLocation.setText(list.getLocation());
        holder.id = list.getId();

    }

    @Override
    public int getItemCount() {
        return listRecuitment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private String id;
        private ImageView imImage;
        private TextView tvTitle;
        private TextView tvCompany;
        private TextView tvLocation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imImage = itemView.findViewById(R.id.image);
            tvTitle = itemView.findViewById(R.id.title);
            tvCompany = itemView.findViewById(R.id.company);
            tvLocation = itemView.findViewById(R.id.location);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {;
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.RECRUITMENT_ID, id);
                    v.getContext().startActivity(intent);
                }
            });
        }
        public void setImageFood(String image) {
            Glide.with(itemView.getContext())
                    .load(image)
                    .fitCenter()
                    .into(imImage);
        }
    }
}
