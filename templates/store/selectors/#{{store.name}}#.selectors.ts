import { createFeatureSelector } from '@ngrx/store';

import { #{{store.name}}#FeatureName } from '../consts';
import { #{{store.name.cap}}#State } from '../models';

export const #{{store.name}}#FeatureSelector = createFeatureSelector<#{{store.name.cap}}#State>(
    #{{store.name}}#FeatureName
);
