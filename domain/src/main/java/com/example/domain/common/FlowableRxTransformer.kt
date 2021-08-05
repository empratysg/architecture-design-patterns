package com.example.domain.common

import io.reactivex.rxjava3.core.FlowableTransformer


abstract class FlowableRxTransformer<T>: FlowableTransformer<T, T>