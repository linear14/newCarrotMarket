# Generated by Django 3.1 on 2020-08-28 11:39

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='User',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('uid', models.TextField()),
                ('nickname', models.CharField(max_length=16)),
                ('profileImageUrl', models.TextField()),
            ],
        ),
    ]
