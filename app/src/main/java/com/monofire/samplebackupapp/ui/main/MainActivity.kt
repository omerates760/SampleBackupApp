package com.monofire.samplebackupapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.work.WorkInfo
import com.monofire.samplebackupapp.data.model.UserEntity
import com.monofire.samplebackupapp.databinding.ActivityMainBinding
import com.monofire.samplebackupapp.ui.main.adapter.UserAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initUI()
        initObservers()


    }

    private fun initObservers() {
        viewModel.userListFromLocal.observe(this) {
            adapter.updateList(it)
        }

        viewModel.backupWorkerState.observe(this, { backupInfo ->
            if (backupInfo.isNotEmpty()) {
                binding.txtLastBackupState.text = when (backupInfo[0].state) {
                    WorkInfo.State.ENQUEUED -> {
                        WorkInfo.State.ENQUEUED.name
                    }
                    WorkInfo.State.CANCELLED -> {
                        WorkInfo.State.CANCELLED.name

                    }
                    WorkInfo.State.RUNNING -> {
                        WorkInfo.State.RUNNING.name

                    }
                    WorkInfo.State.SUCCEEDED -> {
                        WorkInfo.State.SUCCEEDED.name

                    }
                    WorkInfo.State.FAILED -> {
                        WorkInfo.State.FAILED.name

                    }
                    else -> {
                        WorkInfo.State.BLOCKED.name

                    }
                }
            }

        })
    }

    private fun initUI() {
        with(binding) {
            rcViewUser.adapter = adapter
            btnAddUser.setOnClickListener {
                if (!edtUserName.text.isNullOrEmpty()) {
                    viewModel.insertUser(UserEntity(name = edtUserName.text.toString()))
                }
            }
            btnDeleteUsersLocal.setOnClickListener {
                viewModel.deleteAllUserFromLocal()
            }
            btnBackupUsers.setOnClickListener {
                viewModel.startBackupWithOneTime()
            }
            btnCancelBackupUsers.setOnClickListener {
                viewModel.cancelBackup()
            }
            btnFetchUsersFromRemote.setOnClickListener {
                viewModel.fetchUserListFromRemote().observe(this@MainActivity) { users ->
                    if (users.isNotEmpty()) {
                        adapter.updateList(users)
                    }
                }

            }
            btnBackupUserPeriodic.setOnClickListener {
                viewModel.startBackupWithPeriodicTime()
            }

        }

    }
}